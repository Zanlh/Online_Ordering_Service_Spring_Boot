package com.example.Order;

import java.util.List;

import com.example.Order.Models.Customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
				
				// HttpHeaders headers = new HttpHeaders();
				// HttpEntity<String> entity = new HttpEntity<String>(null, headers);

				// ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/customers" ,
				// HttpMethod.GET, entity, String.class);
				
				// log.info(response.toString());

				// String customerId="/1";
				// Customer customer = restTemplate.getForObject("http://localhost:8080/customers"+customerId, Customer.class);
				// System.out.println(customer.getCustomerContact().getName());
				//Assert.assertNotNull(response.getBody());
		};		
	}

}
