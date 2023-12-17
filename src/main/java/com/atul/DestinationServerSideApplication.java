package com.atul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;



@SpringBootApplication
@PropertySource(value = { "messages.properties" })
public class DestinationServerSideApplication {

	public static void main(String[] args) {
		SpringApplication.run(DestinationServerSideApplication.class, args);
	}

}
