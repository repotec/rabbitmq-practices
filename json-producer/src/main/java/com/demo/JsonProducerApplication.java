package com.demo;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JsonProducerApplication implements CommandLineRunner {
	
	@Autowired
	EmployeeJsonProducer employeeJsonProducer;
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(JsonProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		for(int i = 0; i < 5; i++) {
			employeeJsonProducer.sendEmployee(new Employee(i, "name " + i, LocalDate.now()));
		}	
	}

}
