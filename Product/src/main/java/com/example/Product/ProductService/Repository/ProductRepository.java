package com.example.Product.ProductService.Repository;


import java.util.List;

import com.example.Product.ProductService.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    //@Query("SELECT id FROM product p WHERE p.name = ?1 ")
    Product findByName(String name);
    
}
