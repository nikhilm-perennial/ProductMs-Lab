package xyz.mynt.wcbootcamp.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.mynt.wcbootcamp.dto.ProductDTO;
import xyz.mynt.wcbootcamp.dto.ProductsDTO;
import xyz.mynt.wcbootcamp.dto.ReservedProductDTO;
import xyz.mynt.wcbootcamp.entity.ProductEntity;
import xyz.mynt.wcbootcamp.enums.CategoryEnum;
import xyz.mynt.wcbootcamp.repository.ProductRepository;
import xyz.mynt.wcbootcamp.service.ProductService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static xyz.mynt.wcbootcamp.utility.MapperUtils.reservedProductDTO;
import static xyz.mynt.wcbootcamp.utility.MapperUtils.toProductDTO;
import static xyz.mynt.wcbootcamp.utility.RandomGeneratorUtils.generateRandomUUID;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

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
        ProductEntity entity = product.get();
        ProductDTO productDTO = toProductDTO(entity);
        return productDTO;
    }

    @Override
    public ProductEntity addProduct(ProductEntity product) {
        product.setId(generateRandomUUID());
        product.setReservedQuantity(0);
        productRepository.save(product);
        log.info("Product added ->",product);
        return product;
    }

    @Override
    public ReservedProductDTO updateQuantity(String id, ProductEntity product) {
        Optional<ProductEntity> productEntity = productRepository.findById(id);
        ProductEntity entity = productEntity.get();
        int value = entity.getInStock();
        int updateReservedQuantity = entity.getReservedQuantity() + product.getReservedQuantity();
        if (entity.getInStock() > product.getReservedQuantity()){
            entity.setReservedQuantity(updateReservedQuantity);
            entity.setInStock(value - product.getReservedQuantity());
        }
        productRepository.save(entity);
        ReservedProductDTO productDTO = reservedProductDTO(entity);

        return productDTO;
    }



}
