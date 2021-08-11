package com.bezkoder.spring.files.upload.controller;

import com.bezkoder.spring.files.upload.model.Product;
import com.bezkoder.spring.files.upload.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/product")
    public List<Product> displayProduct (){
        return productRepository.findAll();
    }

    @DeleteMapping("/product/{productId}")
    public Optional<ResponseEntity<String>> deleteAgence(@PathVariable (value = "productId") Long productId){
        return productRepository.findById(productId).map(product->{
            productRepository.delete(product);
            return ResponseEntity.ok().body("Successfully deleted specified record");
        });
    }

    @PostMapping("/product")
    public Product displayProduct (@RequestBody Product product){
        return productRepository.save(product);
    }
}
