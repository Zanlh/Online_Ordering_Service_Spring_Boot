package com.example.Product.ProductService.Service;

import java.util.List;

import com.example.Product.ProductService.Models.ProductDetail;
import com.example.Product.ProductService.Repository.ProductDetailRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductDetailService {

    private final ProductDetailRepository productDetailRepository;

    @Autowired

    public ProductDetailService(ProductDetailRepository productDetailRepository) {
        this.productDetailRepository = productDetailRepository;
    }

    public List<ProductDetail> allDetails(){
        return productDetailRepository.findAll();
    }

    public ProductDetail oneDetail(Long id){
        return productDetailRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public ProductDetail newDetail(ProductDetail productDetail){
        return productDetailRepository.save(productDetail);
    }

    public ProductDetail updateDetail(ProductDetail newDetail, Long id){
        return productDetailRepository.findById(id)
        .map(detail -> {
            detail.setDescription(newDetail.getDescription());
            detail.setComment(newDetail.getComment());
            return productDetailRepository.save(detail);
        })
        .orElseGet(() -> {
            newDetail.setId(id);
            return productDetailRepository.save(newDetail);
        });
    }

    public void deleteDetail(Long id){
        productDetailRepository.deleteById(id);
    }
}
