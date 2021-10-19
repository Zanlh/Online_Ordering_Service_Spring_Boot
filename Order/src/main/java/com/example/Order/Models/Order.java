package com.example.Order.Models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "order_table")
public class Order  {
    private @Id @GeneratedValue(strategy = GenerationType.AUTO) Long id;
    @Column
    private Long supplier;
    private String product;
    private int quantity;

    public Order() {
    }


    public Order( Long supplier, String product, int quantity) {
        this.supplier = supplier;
        this.product = product;
        this.quantity = quantity;
    }
    


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSupplier() {
        return this.supplier;
    }

    public void setSupplier(Long supplier) {
        this.supplier = supplier;
    }

    public String getProduct() {
        return this.product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", supplier='" + getSupplier() + "'" +
            ", product='" + getProduct() + "'" +
            ", quantity='" + getQuantity() + "'" +
            "}";
    }


   
}
