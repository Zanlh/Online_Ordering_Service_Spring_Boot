package com.example.Customer;

import com.example.Customer.CustomerService.Models.Customer;
import com.example.Customer.CustomerService.Models.CustomerContact;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		final RestTemplate restTemplate = new RestTemplate();
		return restTemplate;
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception{
		return args -> {

			Customer customer1 = new Customer();
			customer1.setCompanyName("Google");
			customer1.setAddress("New York");
			customer1.setCountry("USA");
			CustomerContact contact1 = new CustomerContact();
			contact1.setName("Sundar Pichai");
			contact1.setPhone("123456789");
			contact1.setEmail("Pichai@gmail.com");
			contact1.setPosition("CEO");
			ResponseEntity<Customer> postCustomer1 = restTemplate.postForEntity("http://localhost:8080/customers", customer1, Customer.class);
			ResponseEntity<CustomerContact> postCustomerContact1 = restTemplate.postForEntity("http://localhost:8080/contacts", contact1, CustomerContact.class);
			restTemplate.put("http://localhost:8080/customers/1/contacts/2",Customer.class);
		};
	}

}
