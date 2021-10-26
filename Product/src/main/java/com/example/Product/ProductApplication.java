package com.example.Product;

import com.example.Product.ProductService.Models.Product;
import com.example.Product.ProductService.Models.ProductDetail;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		final RestTemplate restTemplate = new RestTemplate();
		return restTemplate;
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception{
		return args -> {

			Product product1 = new Product();
			product1.setProductCategory("Electronics");
			product1.setName("Monitor");
			product1.setPrice("300");
			product1.setStockQuantity(10);
			ProductDetail detail1 = new ProductDetail();
			detail1.setDescription("ACER Monitor");
			detail1.setComment("1080p OLED Monitor");
			ResponseEntity<Product> postProduct1 = restTemplate.postForEntity("http://localhost:8082/products", product1, Product.class);
			ResponseEntity<ProductDetail> postProductDetail1 = restTemplate.postForEntity("http://localhost:8082/details", detail1, ProductDetail.class);
			restTemplate.put("http://localhost:8082/products/1/details/2",Product.class);

			Product product2 = new Product();
			product2.setProductCategory("Electronics");
			product2.setName("Microphone");
			product2.setPrice("150");
			product2.setStockQuantity(3);
			ProductDetail detail2 = new ProductDetail();
			detail2.setDescription("Shure Microphone");
			detail2.setComment("High quality audio dynamics");
			ResponseEntity<Product> postProduct2 = restTemplate.postForEntity("http://localhost:8082/products", product2, Product.class);
			ResponseEntity<ProductDetail> postProductDetail2 = restTemplate.postForEntity("http://localhost:8082/details", detail2, ProductDetail.class);
			restTemplate.put("http://localhost:8082/products/3/details/4",Product.class);

			Product product3 = new Product();
			product3.setProductCategory("Electronics");
			product3.setName("Wireless Charger");
			product3.setPrice("30");
			product3.setStockQuantity(50);
			ProductDetail detail3 = new ProductDetail();
			detail3.setDescription("Anker");
			detail3.setComment("15W Wireless Charger");
			ResponseEntity<Product> postProduct3 = restTemplate.postForEntity("http://localhost:8082/products", product3, Product.class);
			ResponseEntity<ProductDetail> postProductDetail3 = restTemplate.postForEntity("http://localhost:8082/details", detail3, ProductDetail.class);
			restTemplate.put("http://localhost:8082/products/5/details/6",Product.class);

			Product product4 = new Product();
			product4.setProductCategory("Furniture");
			product4.setName("Rug");
			product4.setPrice("200");
			product4.setStockQuantity(12);
			ProductDetail detail4 = new ProductDetail();
			detail4.setDescription("Turkish Rug");
			detail4.setComment("Turkish rug made from the finest wool");
			ResponseEntity<Product> postProduct4 = restTemplate.postForEntity("http://localhost:8082/products", product4, Product.class);
			ResponseEntity<ProductDetail> postProductDetail4 = restTemplate.postForEntity("http://localhost:8082/details", detail4, ProductDetail.class);
			restTemplate.put("http://localhost:8082/products/7/details/8",Product.class);		

			Product product5 = new Product();
			product5.setProductCategory("Furniture");
			product5.setName("Desk Chair");
			product5.setPrice("100");
			product5.setStockQuantity(12);
			ProductDetail detail5 = new ProductDetail();
			detail5.setDescription("Herman Miller Executive Chair");
			detail5.setComment("Ergonomic chair with lumbar support");
			ResponseEntity<Product> postProduct5 = restTemplate.postForEntity("http://localhost:8082/products", product5, Product.class);
			ResponseEntity<ProductDetail> postProductDetail5 = restTemplate.postForEntity("http://localhost:8082/details", detail5, ProductDetail.class);
			restTemplate.put("http://localhost:8082/products/9/details/10",Product.class);
		};
	}
}

