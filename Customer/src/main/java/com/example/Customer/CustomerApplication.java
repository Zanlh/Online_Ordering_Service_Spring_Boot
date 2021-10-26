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

			Customer customer2 = new Customer();
			customer2.setCompanyName("Apple");
			customer2.setAddress("San Francisco");
			customer2.setCountry("USA");
			CustomerContact contact2 = new CustomerContact();
			contact2.setName("Tim Cook");
			contact2.setPhone("040513953");
			contact2.setEmail("timapple@gmail.com");
			contact2.setPosition("CEO");
			ResponseEntity<Customer> postCustomer2 = restTemplate.postForEntity("http://localhost:8080/customers", customer2, Customer.class);
			ResponseEntity<CustomerContact> postCustomerContact2 = restTemplate.postForEntity("http://localhost:8080/contacts", contact2, CustomerContact.class);
			restTemplate.put("http://localhost:8080/customers/3/contacts/4");

			Customer customer3 = new Customer();
			customer3.setCompanyName("Cisco");
			customer3.setAddress("San Francisco");
			customer3.setCountry("USA");
			CustomerContact contact3 = new CustomerContact();
			contact3.setName("Chuck Robbins");
			contact3.setPhone("040593102");
			contact3.setEmail("crobbins@gmail.com");
			contact3.setPosition("CEO");
			ResponseEntity<Customer> postCustomer3 = restTemplate.postForEntity("http://localhost:8080/customers", customer3, Customer.class);
			ResponseEntity<CustomerContact> postCustomerContact3 = restTemplate.postForEntity("http://localhost:8080/contacts", contact3, CustomerContact.class);
			restTemplate.put("http://localhost:8080/customers/5/contacts/6");

			Customer customer4 = new Customer();
			customer4.setCompanyName("Microsoft");
			customer4.setAddress("Redmond");
			customer4.setCountry("USA");
			CustomerContact contact4 = new CustomerContact();
			contact4.setName("Steve Jobs");
			contact4.setPhone("049182938");
			contact4.setEmail("steve@gmail.com");
			contact4.setPosition("CEO");
			ResponseEntity<Customer> postCustomer4 = restTemplate.postForEntity("http://localhost:8080/customers", customer4, Customer.class);
			ResponseEntity<CustomerContact> postCustomerContact4 = restTemplate.postForEntity("http://localhost:8080/contacts", contact4, CustomerContact.class);
			restTemplate.put("http://localhost:8080/customers/7/contacts/8");

			Customer customer5 = new Customer();
			customer5.setCompanyName("Advanced Micro Devices");
			customer5.setAddress("Santa Clara");
			customer5.setCountry("USA");
			CustomerContact contact5 = new CustomerContact();
			contact5.setName("Lisa Su");
			contact5.setPhone("049178271");
			contact5.setEmail("lisasue@gmail.com");
			contact5.setPosition("CEO");
			ResponseEntity<Customer> postCustomer5 = restTemplate.postForEntity("http://localhost:8080/customers", customer5, Customer.class);
			ResponseEntity<CustomerContact> postCustomerContact5 = restTemplate.postForEntity("http://localhost:8080/contacts", contact5, CustomerContact.class);
			restTemplate.put("http://localhost:8080/customers/9/contacts/10");
		};
	}

}
