package xyz.mynt.wcbootcamp.dao;

import xyz.mynt.wcbootcamp.entity.ProductEntity;

import java.util.List;

public interface ProductDAO {

    ProductEntity save(ProductEntity productEntity);

    ProductEntity getProductById(String id);

    void deleteProduct(String id);

    ProductEntity updateProduct(String productId, ProductEntity product);

    List<ProductEntity> getAllProducts();
}
