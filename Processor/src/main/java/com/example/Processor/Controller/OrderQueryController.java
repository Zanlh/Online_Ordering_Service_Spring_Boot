package com.example.Processor.Controller;

import java.util.List;

import com.example.Processor.Service.OrderInteractiveQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderQueryController {
    
    @Autowired
    OrderInteractiveQuery orderInteractiveQuery;

    @GetMapping("/order/{productName}/quantity")
    long getOrderQuantityByName(@PathVariable String productName){
        return orderInteractiveQuery.getOrderQuantity(productName);
    }

    @GetMapping("/order/{customerID}/products")
    List<String> getAllProductsByCustomer(@PathVariable Long customerID){
        return orderInteractiveQuery.getProductListByCustomer(customerID);
    }
}

