package com.example.Customer.CustomerService.Repository;

import com.example.Customer.CustomerService.Models.Customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long>{
    
}
