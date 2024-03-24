package com.pratice.repository;

import com.pratice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findProductByUuid(String Uuid) ;
    Boolean existsByName(String name);
    Boolean existsByUuid(String Uuid);
    List<Product> findProductByNameAndStatus (String name , Boolean status);
}
