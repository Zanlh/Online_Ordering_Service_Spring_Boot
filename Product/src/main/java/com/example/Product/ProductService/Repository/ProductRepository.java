package com.example.Product.ProductService.Repository;


import com.example.Product.ProductService.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ProductRepository extends JpaRepository<Product,Long> {

    //@Query("SELECT id FROM product p WHERE p.name = ?1 ")
    Long findByName (String name);
    
}
