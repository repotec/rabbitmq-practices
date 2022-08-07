package com.demo;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entity.InvoiceCancelledMessage;
import com.demo.entity.InvoiceCreatedMessage;
import com.demo.entity.InvoicePaidMessage;

@Service
public class InvoiceProducer {
	
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	private static final String EXCHANGE = "x.invoice";
	
	public void sendInvoiceCreated(InvoiceCreatedMessage message) {
		rabbitTemplate.convertAndSend(EXCHANGE, "", message);
	}
	
	public void sendInvoicePaid(InvoicePaidMessage message) {
		rabbitTemplate.convertAndSend(EXCHANGE, "", message);
	}
	
	public void sendInvoiceCancelled(InvoiceCancelledMessage message) {
		rabbitTemplate.convertAndSend(EXCHANGE, "", message);
	}
}
