package csci318.demo;

import csci318.demo.model.BrandQuantity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;

@SpringBootApplication
public class SpringBootKafkaConsumer {

	private static final Logger log = LoggerFactory.getLogger(SpringBootKafkaConsumer.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringBootKafkaConsumer.class, args);
	}

	@Bean
	public Consumer<BrandQuantity> consume() {
		return input -> log.info(input.toString());
	}
}
