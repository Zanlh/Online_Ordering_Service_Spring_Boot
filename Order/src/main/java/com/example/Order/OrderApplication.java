package com.example.Order;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.example.Order.Models.Customer;
import com.example.Order.Models.Order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class OrderApplication {

	
	private static final Logger log = LoggerFactory.getLogger(OrderApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		final RestTemplate restTemplate = new RestTemplate();
		return restTemplate;
	}


  
	@Bean
	public CommandLineRunner run(RestTemplate restTemplate,StreamBridge streamBridge) throws Exception {
		return args -> {
			String[] ids = new String[]{"1","3","5","7","9"};
			String[] quantity = new String[]{"7","8","2","3","4","5","6","1","9"};
			String[] product = new String[]{"Monitor","Microphone","Wireless Charger","Rug","Desk Chair"};
				try {
					
					while (!Thread.currentThread().isInterrupted()){
						int rndId = new Random().nextInt(ids.length);
						int rndQuantity =  new Random().nextInt(quantity.length);
						int rndProduct = new Random().nextInt(product.length);
						Order orders = restTemplate.postForObject("http://localhost:8081/order/"+ids[rndId]+"/"+product[rndProduct]+"/1"/*+quantity[rndQuantity]*/,Order.class.toString(),Order.class);
						log.info(orders.toString());
						streamBridge.send("order-outbound", orders);
						Thread.sleep(2000);
					}
				}
				catch(InterruptedException ignored){}
				
	};

}
}
