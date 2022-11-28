package xyz.mynt.wcbootcamp.service;

import xyz.mynt.wcbootcamp.dto.ProductDTO;
import xyz.mynt.wcbootcamp.dto.ProductsDTO;
import xyz.mynt.wcbootcamp.entity.ProductEntity;

import java.util.List;

public interface ProductService {

    ProductsDTO getProducts();

    ProductDTO getProductById(String id);

    ProductEntity addProduct(ProductEntity product);

    ProductEntity updateQuantity(String id);


    List<ProductEntity> getPreAddedProducts();
}
