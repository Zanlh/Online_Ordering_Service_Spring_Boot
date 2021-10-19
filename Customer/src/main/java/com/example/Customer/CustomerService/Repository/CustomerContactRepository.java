package com.example.Customer.CustomerService.Repository;

import com.example.Customer.CustomerService.Models.CustomerContact;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerContactRepository extends JpaRepository<CustomerContact,Long>{
    
}
