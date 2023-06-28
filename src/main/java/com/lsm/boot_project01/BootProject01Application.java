package com.lsm.boot_project01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BootProject01Application {

	public static void main(String[] args) {
		SpringApplication.run(BootProject01Application.class, args);
	}

}
