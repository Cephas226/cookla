package com.bezkoder.spring.files.upload.repository;

import com.bezkoder.spring.files.upload.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    void deleteByproductId(Long productId);
}
