package com.demo;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.demo.entity.InvoiceCreatedMessage;

@SpringBootApplication
public class Application implements CommandLineRunner {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}
	
	@Autowired
	InvoiceProducer producer;

	@Override
	public void run(String... args) throws Exception {
		for (int i = 0; i < 200; i++) {
			var randomInvNo = "INV-" + (i % 60);
			var icrm = new InvoiceCreatedMessage(19.2, LocalDate.now().minusDays(2), "USD", randomInvNo);
			
			producer.sendInvoiceCreated(icrm);
		}
	}
}
