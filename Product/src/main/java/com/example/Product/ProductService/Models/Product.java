package com.example.Product.ProductService.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "product")
public class Product {
    private @Id @GeneratedValue (strategy = GenerationType.AUTO) Long id;
    @Column
    private String productCategory;
    private String name;
    private String price;
    private int stockQuantity;
    @OneToOne
    @JoinColumn(name = "product_detail_id")
    private ProductDetail productDetail;


    public Product() {
    }

    public Product(String productCategory, String name, String price, int stockQuantity, ProductDetail productDetail) {
        this.productCategory = productCategory;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.productDetail = productDetail;
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductCategory() {
        return this.productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return this.stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public ProductDetail getProductDetail() {
        return this.productDetail;
    }

    public void setProductDetail(ProductDetail productDetail) {
        this.productDetail = productDetail;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", productCategory='" + getProductCategory() + "'" +
            ", name='" + getName() + "'" +
            ", price='" + getPrice() + "'" +
            ", stockQuantity='" + getStockQuantity() + "'" +
            ", productDetail='" + getProductDetail() + "'" +
            "}";
    }

}
