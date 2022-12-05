package xyz.mynt.wcbootcamp.service.impl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import xyz.mynt.wcbootcamp.dto.ProductsDTO;
import xyz.mynt.wcbootcamp.entity.ProductEntity;
import xyz.mynt.wcbootcamp.enums.CategoryEnum;
import xyz.mynt.wcbootcamp.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import  static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void getProductFromDatabase() {
//        ProductEntity product = new ProductEntity("dswdw3fdxd","Samosa",
//                CategoryEnum.DAIRY_AND_CHILLED, BigDecimal.valueOf(25.00),20,5,true);
//        ProductEntity product1 = new ProductEntity("ews4535dfdfg","Kachori",
//                CategoryEnum.DAIRY_AND_CHILLED,BigDecimal.valueOf(20.00),25,20,true);
//        List<ProductEntity> list = new ArrayList<>();
//        list.add(product);
//        list.add(product1);
//        when(productRepository.findAll()).thenReturn(list);
//
//        ProductsDTO productEntities = productService.getProductFromDatabase();

    }

    @Test
    void getProductById() {
    }

    @Test
    void addProduct() {

    }

    @Test
    void updateQuantity() {
    }
}