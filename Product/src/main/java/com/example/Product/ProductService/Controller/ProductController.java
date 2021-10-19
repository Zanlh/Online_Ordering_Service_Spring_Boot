package com.example.Product.ProductService.Controller;

import java.util.List;
import java.util.stream.Collectors;

import com.example.Product.ProductService.Models.Product;
import com.example.Product.ProductService.Service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
public class ProductController {

    private final ProductService productService;
    @Autowired

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> all(){
        return productService.allProducts();
    }

    @GetMapping("/products/{id}")
    public Product one(@PathVariable Long id){
        return productService.getOneProduct(id);
    }
    @PostMapping("/products")
    public ResponseEntity<Product> newProduct(@RequestBody Product newProduct){
        return ResponseEntity.ok(productService.newProduct(newProduct)) ;
    }

    @PutMapping("/products/{id}")
     public ResponseEntity<Product> replaceProduct(@RequestBody Product newProduct, @PathVariable Long id){
        return ResponseEntity.ok(productService.updateProduct(newProduct, id));
    }

    @PutMapping("/products/{id}/details/{detailId}")
    public Product updateCustomerContact(@PathVariable Long id, @PathVariable Long detailId) {
        return productService.updateProductDetail(id, detailId);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);

        return ResponseEntity.noContent().build();
    }
    
}

