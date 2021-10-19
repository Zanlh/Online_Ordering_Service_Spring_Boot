package com.example.Customer.CustomerService.NotFoundException;

public class CustomerNotFoundException extends RuntimeException{

    public CustomerNotFoundException(Long id){
        super("Could not find customer " + id);
    }
    
}
