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
	Producer producer;
		
	@Override
	public void run(String... args) throws Exception {
		for (int i = 1; i <= 10; i++) {
			Employee employee = new Employee();
			employee.setName("picture " + i);
			employee.setId(i);
			employee.setSalary(ThreadLocalRandom.current().nextInt(1000, 10000));
			employee.setType(getEmployeeType(i));
			
			producer.sendMessage(employee);
		}
	}
	
	public String getEmployeeType(int i) {
		int flag = ThreadLocalRandom.current().nextInt(10, 20) % i;
		if(flag == 1) 
			return "employee";
		else 
			return "manager";
	}
}
