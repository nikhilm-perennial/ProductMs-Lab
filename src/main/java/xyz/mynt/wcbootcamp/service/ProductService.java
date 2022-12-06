package xyz.mynt.wcbootcamp.service;

import xyz.mynt.wcbootcamp.dto.ProductDTO;
import xyz.mynt.wcbootcamp.dto.ProductsDTO;
import xyz.mynt.wcbootcamp.dto.ReservedProductDTO;
import xyz.mynt.wcbootcamp.entity.ProductEntity;

import java.util.List;

public interface ProductService {

    ProductsDTO getProductFromDatabase();


    ProductDTO getProductById(String id);

    ProductEntity addProduct(ProductEntity product);

    ReservedProductDTO updateQuantity(String id, ProductEntity product);

    void deleteProduct(String id);


}
