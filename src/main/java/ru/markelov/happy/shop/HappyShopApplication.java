package ru.markelov.happy.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:secured.properties")
public class HappyShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(HappyShopApplication.class, args);
	}

}
