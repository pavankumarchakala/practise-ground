package com.practise_ground;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class PractiseGroundApplication {

	public static void main(String[] args) {
		SpringApplication.run(PractiseGroundApplication.class, args);
	}

}
