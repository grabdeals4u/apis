package com.boot.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.users.dao.UserRepository;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.users")
@EnableJpaRepositories("com.users")
@EntityScan("com.users")
public class SpringBootWebApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringBootWebApplication.class, args);
		UserRepository rep = context.getBean(UserRepository.class);
		System.out.println("rep == "+rep);
	}
}
