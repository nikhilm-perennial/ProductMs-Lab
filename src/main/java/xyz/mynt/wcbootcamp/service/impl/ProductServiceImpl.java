package xyz.mynt.wcbootcamp.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import xyz.mynt.wcbootcamp.dto.ProductDTO;
import xyz.mynt.wcbootcamp.dto.ProductsDTO;
import xyz.mynt.wcbootcamp.dto.ReservedProductDTO;
import xyz.mynt.wcbootcamp.entity.ProductEntity;
import xyz.mynt.wcbootcamp.exceptions.ProductNotFoundException;
import xyz.mynt.wcbootcamp.exceptions.ProductOutOfStockException;
import xyz.mynt.wcbootcamp.repository.ProductRepository;
import xyz.mynt.wcbootcamp.service.ProductService;
import xyz.mynt.wcbootcamp.utility.NotificationSenderUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static xyz.mynt.wcbootcamp.utility.MapperUtils.*;
import static xyz.mynt.wcbootcamp.utility.RandomGeneratorUtils.generateRandomUUID;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @Override
    public ProductsDTO getProductFromDatabase() {
        List<ProductEntity> list = productRepository.findAll();
        List<ProductDTO> dtoList = new ArrayList<>();
        for (ProductEntity productEntity : list){
            ProductDTO productDTO = toProductDTO(productEntity);
            dtoList.add(productDTO);
        }
        return ProductsDTO.builder()
                .products(dtoList)
                .build();
    }


    @Override
    public ProductDTO getProductById(String id) {
        Optional<ProductEntity> product = productRepository.findById(id);
        if (product.isEmpty()) throw new ProductNotFoundException("Product not found");
        ProductEntity entity = product.get();
        ProductDTO productDTO = toProductDTO(entity);
        return productDTO;
    }

    @Override
    public ProductEntity addProduct(ProductEntity product) {
        return product;
    }

    @Override
    public ProductDTO addProduct(ProductDTO product) {

        ProductEntity entity = toProductEntity(product);
        entity.setId(generateRandomUUID());
        entity.setReservedQuantity(0);
        ProductDTO productDTO;
        productDTO = toProductDTO(productRepository.save(entity));
        kafkaTemplate.send("Product_Notification", NotificationSenderUtils.generateNotificationBody(productDTO));
        return productDTO;
    }

    @Override
    public ReservedProductDTO updateQuantity(String id, ProductEntity product) {
        Optional<ProductEntity> productEntity = productRepository.findById(id);
        ProductEntity entity = productEntity.get();
        int inStock = entity.getInStock();
        int updateReservedQuantity = entity.getReservedQuantity() + product.getReservedQuantity();
        if (entity.getInStock().equals(0)){
            throw new ProductOutOfStockException("Product out of stock");
        }
        if (entity.getInStock() > product.getReservedQuantity()){
            entity.setReservedQuantity(updateReservedQuantity);
            entity.setInStock(inStock - product.getReservedQuantity());
        }
        productRepository.save(entity);
        ReservedProductDTO productDTO = reservedProductDTO(entity);

        return productDTO;
    }

    @Override
    public void deleteProduct(String id) {
        Optional<ProductEntity> entity = productRepository.findById(id);
        if (entity.isEmpty()){
            throw new ProductNotFoundException("Product not found");
        }
        ProductEntity product = entity.get();
        productRepository.delete(product);
    }
}
