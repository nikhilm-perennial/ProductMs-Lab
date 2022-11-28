package xyz.mynt.wcbootcamp.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xyz.mynt.wcbootcamp.dto.ProductDTO;
import xyz.mynt.wcbootcamp.dto.ProductsDTO;
import xyz.mynt.wcbootcamp.entity.ProductEntity;
import xyz.mynt.wcbootcamp.enums.CategoryEnum;
import xyz.mynt.wcbootcamp.service.ProductService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static xyz.mynt.wcbootcamp.utility.MapperUtils.toProductDTO;
import static xyz.mynt.wcbootcamp.utility.RandomGeneratorUtils.generateRandomUUID;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    List<ProductEntity> products = new ArrayList<>();



    @Override
    public ProductsDTO getProducts() {
        List<ProductEntity> preAddedProducts = getPreAddedProducts();
        List<ProductDTO> products = new ArrayList<>();

        for (ProductEntity productEntity: preAddedProducts) {
            ProductDTO product = toProductDTO(productEntity);
            products.add(product);
        }

        return ProductsDTO.builder()
                .products(products)
                .build();
    }

    @Override
    public ProductDTO getProductById(String id) {

        List<ProductEntity> getAddedProducts = products;
        
        ProductDTO productDTO = null;
        for (ProductEntity productEntity : getAddedProducts){
            if (productEntity.getId().equals(id))
                 productDTO = toProductDTO(productEntity);
        }
        return productDTO;
    }

    @Override
    public ProductEntity addProduct(ProductEntity product) {
        product.setId(generateRandomUUID());
        products.add(product);
        log.info("Product added ->",product);
        return product;
    }

    @Override
    public ProductEntity updateQuantity(String id) {
        ProductEntity productEntity = new ProductEntity();
//        productEntity = products.get(productEntity.getId().equals(id));

        return null;
    }


    @Override
    public List<ProductEntity> getPreAddedProducts() {


//        ProductEntity apple = new ProductEntity();
//        apple.setId(generateRandomUUID());
//        apple.setName("apple");
//        apple.setPrice(BigDecimal.valueOf(25));
//        apple.setCategory(CategoryEnum.FRUITS_AND_VEGETABLES);
//        apple.setInStock(100);
//        apple.setReservedQuantity(20);
//        apple.setAvailable(true);
//        products.add(apple);
//
//        ProductEntity milk = new ProductEntity();
//        milk.setId(generateRandomUUID());
//        milk.setName("milk");
//        milk.setPrice(BigDecimal.valueOf(110));
//        milk.setCategory(CategoryEnum.DAIRY_AND_CHILLED);
//        milk.setInStock(30);
//        milk.setReservedQuantity(2);
//        milk.setAvailable(true);
//        products.add(milk);

        return products;
    }
}
