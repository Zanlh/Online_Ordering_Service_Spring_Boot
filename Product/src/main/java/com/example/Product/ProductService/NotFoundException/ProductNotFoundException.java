package com.example.Product.ProductService.NotFoundException;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Long id){
        super("Could not find product "+ id);
    }
    
}
