package com.demo;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}
	
	@Autowired
	HumanResourceProducer humanResourceProducer;

	@Override
	public void run(String... args) throws Exception {
		for (int i = 0; i < 5; i++) {
			humanResourceProducer.sendMessage(new Employee(i+1, "employee " + (i+1), LocalDate.now()));
		}
	}
}
