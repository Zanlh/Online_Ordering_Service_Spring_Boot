package com.example.Order.Service;

import java.util.List;

import com.example.Order.Exception.OrderNotFoundException;
import com.example.Order.Models.Customer;
import com.example.Order.Models.Order;
import com.example.Order.Models.Product;
import com.example.Order.Repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class OrderService { 
    private ApplicationEventPublisher publisher;
    private final OrderRepository orderRepository;
    
    @Autowired

    public OrderService(ApplicationEventPublisher publisher,OrderRepository orderRepository) {
        this.publisher = publisher;
        this.orderRepository = orderRepository;
    }

    public void recordOrder(Long customerId,String productName, int quantity){
        Order newOrder = new Order(customerId, productName,quantity);
        publisher.publishEvent(newOrder);
    }

    public List<Order> allOrders(){
        return orderRepository.findAll();
    }
    
}
