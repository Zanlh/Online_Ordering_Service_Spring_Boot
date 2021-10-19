package com.example.Order.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer  {
    private Long id;
    private String companyName;
    private String address;
    private String country;
    private CustomerContact customerContact;
    
    public Customer() {
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
