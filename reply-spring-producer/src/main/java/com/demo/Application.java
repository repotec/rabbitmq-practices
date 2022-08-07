package com.demo;

import java.util.concurrent.ThreadLocalRandom;

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
	HumanResourceProducer producer;
		
	@Override
	public void run(String... args) throws Exception {
		for (int i = 1; i <= 10; i++) {
			Employee employee = new Employee();
			employee.setId(i);
			employee.setName("emplouee " + i);
			employee.setSalary(ThreadLocalRandom.current().nextInt(1000, 10000));
			
			producer.sendMessage(employee);
		}
	}
}
