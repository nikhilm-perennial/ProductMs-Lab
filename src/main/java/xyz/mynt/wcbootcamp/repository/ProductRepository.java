package xyz.mynt.wcbootcamp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.mynt.wcbootcamp.entity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,String> {
}
