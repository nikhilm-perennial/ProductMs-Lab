package xyz.mynt.wcbootcamp.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import xyz.mynt.wcbootcamp.dao.ProductDAO;
import xyz.mynt.wcbootcamp.entity.ProductEntity;
import xyz.mynt.wcbootcamp.exceptions.ProductNotFoundException;
import xyz.mynt.wcbootcamp.exceptions.ProductOutOfStockException;
import xyz.mynt.wcbootcamp.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductDAOImpl implements ProductDAO {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductEntity save(ProductEntity productEntity) {
        return productRepository.save(productEntity);
    }

    @Override
    @Cacheable(key = "#id",value = "ProductEntity")
    public ProductEntity getProductById(String id) {
        Optional<ProductEntity> entity = productRepository.findById(id);
        if (entity.isEmpty())
            throw new ProductNotFoundException("Product Not Found");
        ProductEntity product = entity.get();
        return product;
    }

    @Override
    @CacheEvict(key = "#id",value = "ProductEntity",allEntries = false)
    public void deleteProduct(String id) {
        Optional<ProductEntity> product = productRepository.findById(id);
        ProductEntity entity = product.get();
        productRepository.delete(entity);

    }

    @Override
    @CachePut(key = "#productId",value = "ProductEntity")
    public ProductEntity updateProduct(String productId, ProductEntity product) {
        Optional<ProductEntity> entity = productRepository.findById(productId);
        ProductEntity productEntity = entity.get();
        int inStock = productEntity.getInStock();
        int updateReservedQuantity = productEntity.getReservedQuantity() + product.getReservedQuantity();
        if (productEntity.getInStock().equals(0)){
            throw new ProductOutOfStockException("Product out of stock");
        }
        if (productEntity.getInStock() > product.getReservedQuantity()){
            productEntity.setReservedQuantity(updateReservedQuantity);
            productEntity.setInStock(inStock - product.getReservedQuantity());
        }
        productRepository.save(productEntity);
        return productEntity;
    }

    @Override
    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }



}
