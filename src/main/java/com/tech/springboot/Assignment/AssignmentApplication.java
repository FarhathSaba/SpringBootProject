package com.tech.springboot.Assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.tech.springboot.*"})
@EnableJpaRepositories("com.tech.springboot..dao")
@EntityScan("com.tech.springboot.entities")
public class AssignmentApplication {

	public static void main(String[] args) {
		Starters.startH2Server();
		SpringApplication.run(AssignmentApplication.class, args);
	}

}
