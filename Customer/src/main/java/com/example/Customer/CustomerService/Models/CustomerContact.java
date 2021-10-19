package com.example.Customer.CustomerService.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "customer_contact")
public class CustomerContact {

    private @Id @GeneratedValue(strategy = GenerationType.AUTO) Long id;
    @Column(nullable = false)
    private String name;
    private String phone;
    private String email;
    private String position;
    @OneToOne (mappedBy = "customerContact")
    @JsonIgnore
    private Customer customer;


    public CustomerContact() {
    }

    public CustomerContact( String name, String phone, String email, String position, Customer customer) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.position = position;
        this.customer = customer;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", phone='" + getPhone() + "'" +
            ", email='" + getEmail() + "'" +
            ", position='" + getPosition() + "'" +
            ", customer='" + getCustomer() + "'" +
            "}";
    }
}