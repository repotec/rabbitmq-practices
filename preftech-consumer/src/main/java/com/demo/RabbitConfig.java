package com.demo;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

@Configuration
public class RabbitConfig {
	
	@Bean
	public ObjectMapper objectMapper() {
		return JsonMapper.builder().findAndAddModules().build();
	}
}
