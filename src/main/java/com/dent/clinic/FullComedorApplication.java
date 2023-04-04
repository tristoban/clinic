package com.dent.clinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class FullComedorApplication {

	public static void main(String[] args) {
		SpringApplication.run(FullComedorApplication.class, args);
	}

}
