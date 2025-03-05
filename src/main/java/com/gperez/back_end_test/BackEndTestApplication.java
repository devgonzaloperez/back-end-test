package com.gperez.back_end_test;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackEndTestApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BackEndTestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("[Application Running]");
	}

}
