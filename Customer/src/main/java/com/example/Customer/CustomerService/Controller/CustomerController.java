package com.example.Customer.CustomerService.Controller;

import java.util.List;

import com.example.Customer.CustomerService.Models.Customer;
import com.example.Customer.CustomerService.Service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
public class CustomerController {
    
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    
    @GetMapping("/customers")
    public List<Customer> all(){
        return customerService.getCustomers();
    }

    @GetMapping("/customers/{id}")
    public Customer one(@PathVariable Long id){
        return customerService.getOnecustomer(id);
    }

    @PostMapping("/customers")
    public ResponseEntity<Customer> newCustomer(@RequestBody Customer newCustomer){
        return ResponseEntity.ok(customerService.newCustomer(newCustomer)) ;
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<Customer> replaceCustomer(@RequestBody Customer newCustomer, @PathVariable Long id){
        return ResponseEntity.ok(customerService.updateCustomer(newCustomer, id));
    }

     @PutMapping("/customers/{id}/contacts/{contactId}")
     public Customer updateCustomerContact(@PathVariable Long id, @PathVariable Long contactId) {
        return customerService.updateCustomerContact(id, contactId);
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id){

        customerService.deleteCustomer(id);

        return ResponseEntity.noContent().build();
    }
}
