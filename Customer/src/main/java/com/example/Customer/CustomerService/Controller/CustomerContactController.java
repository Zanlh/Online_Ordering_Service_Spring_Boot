package com.example.Customer.CustomerService.Controller;


import java.util.List;
import com.example.Customer.CustomerService.Models.CustomerContact;
import com.example.Customer.CustomerService.Service.CustomerContactService;
//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerContactController {
    private final CustomerContactService customerContactService;

    @Autowired
    public CustomerContactController(CustomerContactService customerContactService) {
        this.customerContactService = customerContactService;
    }
    

    @GetMapping("/contacts")
    public List<CustomerContact> all(){
        return customerContactService.allContacts();
    }
      
    @GetMapping("/contacts/{id}")
    public CustomerContact  one(@PathVariable Long id){
        return customerContactService.getContactById(id);
    }

    @PostMapping("/contacts")
    public ResponseEntity<CustomerContact> newCustomerContact(@RequestBody CustomerContact newCustomerContact){
        return ResponseEntity.ok(customerContactService.createCustomerContact(newCustomerContact));
    }

    @PutMapping("/contacts/{id}")
    public ResponseEntity<CustomerContact> replaceCustomer(@RequestBody CustomerContact newCustomerContact, @PathVariable Long id){
        return ResponseEntity.ok(customerContactService.replaceCustomerContact(newCustomerContact, id));
    }

    @DeleteMapping("/contacts/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id){

        customerContactService.deleteCustomerContact(id);;

        return ResponseEntity.noContent().build();
    }
}
