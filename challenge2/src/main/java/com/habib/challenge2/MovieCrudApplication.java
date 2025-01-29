package com.habib.challenge2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@EnableJpaRepositories(basePackages = "com.habib.challenge2")
@SpringBootApplication
public class MovieCrudApplication {
	public static void main(String[] args) {
		SpringApplication.run(MovieCrudApplication.class, args);
	}
}
