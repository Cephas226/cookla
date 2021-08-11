package com.bezkoder.spring.files.upload.repository;

import com.bezkoder.spring.files.upload.model.Item;
import com.bezkoder.spring.files.upload.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {

}
