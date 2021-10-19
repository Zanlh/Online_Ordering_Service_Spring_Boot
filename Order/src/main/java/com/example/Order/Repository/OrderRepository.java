package com.example.Order.Repository;

import com.example.Order.Models.Order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long>{
    
}
