package com.example.Product.ProductService.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "product_detail")
public class ProductDetail {
    private @Id @GeneratedValue(strategy = GenerationType.AUTO) Long id;
    @Column
    private String description;
    private String comment;
    @OneToOne(mappedBy = "productDetail")
    @JsonIgnore
    private Product product;


    public ProductDetail() {
    }

    public ProductDetail( String description, String comment, Product product) {
        this.description = description;
        this.comment = comment;
        this.product = product;
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", description='" + getDescription() + "'" +
            ", comment='" + getComment() + "'" +
            ", product='" + getProduct() + "'" +
            "}";
    }


}
