package com.demo;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SampleDefaultExchangeApplication implements CommandLineRunner {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(SampleDefaultExchangeApplication.class, args);
	}

	@Autowired
	RabbitTemplate rabbitTemplate;
	
	@Override
	public void run(String... args) throws Exception {
		rabbitTemplate.convertAndSend("TestExchange", "TestRouting", "hello from code!");
		
	}
}
