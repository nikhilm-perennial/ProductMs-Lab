package xyz.mynt.wcbootcamp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.mynt.wcbootcamp.dto.ProductDTO;
import xyz.mynt.wcbootcamp.service.impl.ProductServiceImpl;

@RestController
@RequestMapping("/products")
public class ProductController {

    private static final String PRODUCT_ADDED = "Product Added";

    @Autowired
    private ProductServiceImpl productService;

    @PostMapping
    public ResponseEntity<String> addProduct(@RequestBody ProductDTO productDTO){
        productService.saveProduct(productDTO);
        return new ResponseEntity<>(PRODUCT_ADDED, HttpStatus.resolve(201));
    }


}
