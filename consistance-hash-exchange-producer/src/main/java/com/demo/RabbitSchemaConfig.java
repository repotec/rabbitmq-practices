package com.demo;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Binding.DestinationType;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitSchemaConfig {
//	@Bean
//	public Declarables rabbitSchema() {
//		return new Declarables(new  Exchange("x.invoice"),
//							   new Queue("q.invoice.one"),
//							   new Binding("q.invoice.one", DestinationType.QUEUE , "x.invoice", "", null),
//							   new Queue("q.invoice.two"),
//							   new Binding("q.invoice.two", DestinationType.QUEUE , "x.invoice", "", null));
//	}
//	
//	@Consis
}
