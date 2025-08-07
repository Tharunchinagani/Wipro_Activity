package com.monocartUser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class MonocartUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonocartUserApplication.class, args);
	}

}
