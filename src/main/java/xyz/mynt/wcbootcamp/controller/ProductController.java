package xyz.mynt.wcbootcamp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.mynt.wcbootcamp.dto.ProductDTO;
import xyz.mynt.wcbootcamp.entity.ProductEntity;
import xyz.mynt.wcbootcamp.service.impl.ProductServiceImpl;

import java.util.Objects;

@RestController
@Slf4j
@RequestMapping("products")
public class ProductController {

    private static final String PRODUCT_NOT_FOUND = "Product not found";
    private static final String PRODUCT_ADDED = "Product added";
    private static final String PRODUCT_OUT_OF_STOCK = "Product out of stock";
    @Autowired
    private ProductServiceImpl productService;


    @PostMapping
    public ResponseEntity<String> addProduct(@RequestBody ProductEntity productEntity){
        productService.addProduct(productEntity);
        return new ResponseEntity<>(PRODUCT_ADDED,HttpStatus.resolve(201));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable String id) {
        ProductDTO productDTO = productService.getProductById(id);
        if (productDTO==null){
            return new ResponseEntity<>(PRODUCT_NOT_FOUND,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productDTO,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Object> getProductFromDatabase(){
        return new ResponseEntity<>(productService.getProductFromDatabase(),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateReservedQuantity(@PathVariable String id,
                                                         @RequestBody ProductEntity product){
        return new ResponseEntity<>(productService.updateQuantity(id,product),HttpStatus.OK);
    }
}
