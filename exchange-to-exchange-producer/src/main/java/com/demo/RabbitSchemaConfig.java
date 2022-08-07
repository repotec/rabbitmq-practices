package com.demo;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Binding.DestinationType;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitSchemaConfig {
	
	@Bean
	public Declarables rabbitSchema() {
		return new Declarables(
			   new DirectExchange("x.home"),
			   new DirectExchange("x.linked"),
			   
			   new Queue("q.mobile"),
			   new Queue("q.laptop"),
			   new Queue("q.fan"),
			   new Queue("q.AC"),
			   new Queue("q.light"),
			   
			   new Binding("q.fan", DestinationType.QUEUE, "x.home", "appliance", null),
			   new Binding("q.laptop", DestinationType.QUEUE, "x.home", "personal", null),
			   
			   new Binding("q.mobile", DestinationType.QUEUE, "x.linked", "personal", null),
			   new Binding("q.AC", DestinationType.QUEUE, "x.linked", "appliance", null),
			   new Binding("q.light", DestinationType.QUEUE, "x.linked", "appliance", null),
			   
			   new Binding("x.linked", DestinationType.EXCHANGE, "x.home", "appliance", null)
		);
	}
}

