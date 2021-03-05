package ru.markelov.happy.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableAspectJAutoProxy
@PropertySource("classpath:secured.properties")
//@ComponentScan("ru.markelov.happy.shop")
public class HappyShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(HappyShopApplication.class, args);
	}

}
