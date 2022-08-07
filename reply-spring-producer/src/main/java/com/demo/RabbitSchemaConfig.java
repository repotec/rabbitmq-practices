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
	
	@Bean
	public Declarables rabbitSchema() {
		return new Declarables(new FanoutExchange("x.hr.register"),
							   new FanoutExchange("x.hr.register.refused"),
							   
							   new Queue("q.hr.register", true, false, false, null),
							   new Queue("q.hr.register.refused", true, false, false, null),
							   
							   new Binding("q.hr.register", DestinationType.QUEUE, "x.hr.register", "", null),
							   new Binding("q.hr.register.refused", DestinationType.QUEUE, "x.hr.register.refused", "", null));
	}
}