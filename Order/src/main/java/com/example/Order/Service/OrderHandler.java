package com.example.Order.Service;

import com.example.Order.Models.Order;
import com.example.Order.Repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service

public class OrderHandler {
    private OrderRepository orderRepository;

    @Autowired
    public OrderHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    
    @EventListener
    public void handle(Order order){
        orderRepository.save(order);
    }

}
