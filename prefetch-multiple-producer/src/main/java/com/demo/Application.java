package com.demo;

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
	MultiplePrefetchProducer producer;

	@Override
	public void run(String... args) throws Exception {
		producer.sendTransMessage();
		producer.sendAccsMessage();
	}
}
