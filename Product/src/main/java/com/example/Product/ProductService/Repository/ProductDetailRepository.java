package com.example.Product.ProductService.Repository;

import com.example.Product.ProductService.Models.ProductDetail;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDetailRepository extends JpaRepository<ProductDetail,Long> {
    
}
