package com.example.Customer.CustomerService.Models;


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
@Table(name = "customer")
public class Customer  {
    private @Id @GeneratedValue(strategy = GenerationType.AUTO) Long id;
    @Column
    private String companyName;
    private String address;
    private String country;
    @OneToOne
    @JoinColumn(name = "customer_contact_id")
    private CustomerContact customerContact;
    public Customer() {
    }

    public Customer( String companyName, String address, String country, CustomerContact customerContact) {
        this.companyName = companyName;
        this.address = address;
        this.country = country;
        this.customerContact = customerContact;
    }



    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public CustomerContact getCustomerContact() {
        return this.customerContact;
    }

    public void setCustomerContact(CustomerContact customerContact) {
        this.customerContact = customerContact;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", companyName='" + getCompanyName() + "'" +
            ", address='" + getAddress() + "'" +
            ", country='" + getCountry() + "'" +
            ", customerContact='" + getCustomerContact() + "'" +
            "}";
    }


    // @Override
    // public boolean equals(Object o) {
  
    //   if (this == o)
    //     return true;
    //   if (!(o instanceof Customer))
    //     return false;
    //   Customer customer = (Customer) o;
    //   return Objects.equals(this.ID, customer.ID) && Objects.equals(this.companyName, customer.companyName)
    //       && Objects.equals(this.address, customer.address) && Objects.equals(this.country, customer.country);
    // }

    // @Override
    // public int hashCode() {
    //   return Objects.hash(this.ID, this.companyName, this.address, this.country);
    // }   
   
    
    
}
