package com.example.Customer.CustomerService.Service;

import java.util.List;
import com.example.Customer.CustomerService.Models.Customer;
import com.example.Customer.CustomerService.Models.CustomerContact;
import com.example.Customer.CustomerService.NotFoundException.CustomerNotFoundException;
import com.example.Customer.CustomerService.Repository.CustomerContactRepository;
import com.example.Customer.CustomerService.Repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerContactRepository customerContactRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, CustomerContactRepository customerContactRepository) {
        this.customerRepository = customerRepository;
        this.customerContactRepository = customerContactRepository;
    }
    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    
    }
    
    public Customer getOnecustomer(Long id){
        Customer customer = customerRepository.findById(id)
        .orElseThrow(() -> new CustomerNotFoundException(id));
        return customer;
    }

    public Customer newCustomer( Customer newCustomer){
        return customerRepository.save(newCustomer);

    }
    
    public Customer updateCustomer (Customer newCustomer, Long id){
        Customer updateCustomer = customerRepository.findById(id)
        .map(customer ->{
            customer.setCompanyName(newCustomer.getCompanyName());
            customer.setAddress(newCustomer.getAddress());
            customer.setCountry(newCustomer.getCountry());
            return customerRepository.save(customer);
        })
        .orElseGet(() -> {
            newCustomer.setId(id);
            return customerRepository.save(newCustomer);
        });
        return customerRepository.save(updateCustomer);
    }

    public Customer updateCustomerContact( Long id, Long contactId) {
        Customer customer = customerRepository.findById(id).orElseThrow(RuntimeException::new);
        CustomerContact customerContact = customerContactRepository.findById(contactId).orElseThrow(RuntimeException::new);
        customer.setCustomerContact(customerContact);
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long id){
        customerRepository.deleteById(id);
    }

        
    
}
