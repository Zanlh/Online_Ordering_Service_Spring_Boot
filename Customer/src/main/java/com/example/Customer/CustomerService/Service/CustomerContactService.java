package com.example.Customer.CustomerService.Service;

import java.util.List;

import com.example.Customer.CustomerService.Models.CustomerContact;
import com.example.Customer.CustomerService.Repository.CustomerContactRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerContactService {

    private final CustomerContactRepository customerContactRepository;

    @Autowired
    public CustomerContactService(CustomerContactRepository customerContactRepository) {
        this.customerContactRepository = customerContactRepository;
    }

    public List<CustomerContact> allContacts() {
        return customerContactRepository.findAll();
    }

    public CustomerContact getContactById( Long id) {
        return customerContactRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    public  CustomerContact createCustomerContact(CustomerContact newCustomerContact) {
        return customerContactRepository.save(newCustomerContact);
    }

    public CustomerContact replaceCustomerContact(CustomerContact newCustomerContact, Long id){
        return customerContactRepository.findById(id)
        .map(customerContact -> {
            customerContact.setName(newCustomerContact.getName());
            customerContact.setPhone(newCustomerContact.getPhone());
            customerContact.setEmail(newCustomerContact.getEmail());
            customerContact.setPosition(newCustomerContact.getPosition());
            return customerContactRepository.save(customerContact);
        })
        .orElseGet(() -> {
            newCustomerContact.setId(id);
            return customerContactRepository.save(newCustomerContact);
        });
    }

    public void deleteCustomerContact( Long id){
        customerContactRepository.deleteById(id);
    }

    
}
