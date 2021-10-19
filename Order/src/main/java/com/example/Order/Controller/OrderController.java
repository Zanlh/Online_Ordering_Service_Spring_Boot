package com.example.Order.Controller;

import java.util.List;

import com.example.Order.Models.Customer;
import com.example.Order.Models.CustomerContact;
import com.example.Order.Models.Order;
import com.example.Order.Service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
public class OrderController {
    
    private final OrderService orderService;
    private final RestTemplate restTemplate;

    @Autowired

    public OrderController(OrderService orderService, RestTemplate restTemplate) {
        this.orderService = orderService;
        this.restTemplate = restTemplate;
    }
    
    
    @GetMapping("/orders")
    public List<Order> orders(){
        return orderService.allOrders();
    }

    @PostMapping("/order/{customerId}/{productName}/{quantity}")
    public String newOrder(@PathVariable Long customerId,@PathVariable String productName,@PathVariable int quantity) throws Exception{
                             
		Customer customer = restTemplate.getForObject("http://localhost:8080/customers/"+customerId, Customer.class);
        if(!customerId.equals(customer.getId())) throw new Exception("User not found");
           
        if(customerId.equals(customer.getId())){
            orderService.recordOrder(customerId, productName, quantity);
            return customer.getAddress() +" "+customer.getCustomerContact().getPhone();
        }

        return null;
        
		//System.out.println(customer.getCustomerContact().getName());

    }
    


}