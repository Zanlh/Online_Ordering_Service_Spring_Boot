package com.example.Product.ProductService.Controller;

import java.util.List;
import java.util.stream.Collectors;


import com.example.Product.ProductService.Models.ProductDetail;
import com.example.Product.ProductService.Service.ProductDetailService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
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

@RestController
public class ProductDetailController {
    
    private final ProductDetailService productDetailService;

    @Autowired
    public ProductDetailController(ProductDetailService productDetailService) {
        this.productDetailService = productDetailService;
    }

    @GetMapping("/details")
	public List<ProductDetail> all(){
        return productDetailService.allDetails();
    }

    @GetMapping("/details/{id}")
	public ProductDetail one(@PathVariable Long id){
        return productDetailService.oneDetail(id);
    }

    @PostMapping("/details")
	public ResponseEntity<ProductDetail> newDetail(@RequestBody ProductDetail newProductDetail){
        return ResponseEntity.ok(productDetailService.newDetail(newProductDetail));
    }

    @PutMapping("/details/{id}")
	public ResponseEntity<ProductDetail> replaceDetail(@RequestBody ProductDetail newProductDetail, @PathVariable Long id){
        return ResponseEntity.ok(productDetailService.updateDetail(newProductDetail, id));
    }

    @DeleteMapping("/details/{id}")
    ResponseEntity<?> deleteDetail(@PathVariable Long id){

        productDetailService.deleteDetail(id);

        return ResponseEntity.noContent().build();
    }

}

