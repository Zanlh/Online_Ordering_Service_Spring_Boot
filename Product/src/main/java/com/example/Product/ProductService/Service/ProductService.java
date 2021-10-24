package com.example.Product.ProductService.Service;

import java.util.List;

import com.example.Product.ProductService.Models.Product;
import com.example.Product.ProductService.Models.ProductDetail;
import com.example.Product.ProductService.NotFoundException.ProductNotFoundException;
import com.example.Product.ProductService.Repository.ProductDetailRepository;
import com.example.Product.ProductService.Repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    
    private ProductRepository productRepository;
    private ProductDetailRepository productDetailRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductDetailRepository productDetailRepository) {
        this.productRepository = productRepository;
        this.productDetailRepository = productDetailRepository;
    }

    public List<Product> allProducts(){
        return productRepository.findAll();
    }

    public Product getOneProduct(Long id){
        Product product = productRepository.findById(id)
        .orElseThrow(() -> new ProductNotFoundException(id));
        return product;
    }

    public Product newProduct(Product newProduct){
        return productRepository.save(newProduct);
    }

    public Product updateProduct(Product newProduct, Long id){
        Product updateProduct = productRepository.findById(id)
        .map(product ->{
            product.setName(newProduct.getName());
            product.setProductCategory(newProduct.getProductCategory());
            product.setPrice(newProduct.getPrice());
            product.setStockQuantity(newProduct.getStockQuantity());
            return productRepository.save(product);
        })
        .orElseGet(() ->{
            newProduct.setId(id);
            return productRepository.save(newProduct);
        });
        return productRepository.save(updateProduct);
    }

    public Product updateProductDetail(Long id, Long detailId){
        Product product = productRepository.findById(id).orElseThrow(RuntimeException::new);
        ProductDetail productDetail = productDetailRepository.findById(detailId).orElseThrow(RuntimeException::new);
        product.setProductDetail(productDetail);
        return productRepository.save(product);
    }

    public Product getProductByName(String productName){
        Product product = productRepository.findById(productRepository.findByName(productName)).orElseThrow(RuntimeException::new);
        return product;
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }
    

}
