package com.bgc.backgroundVerification.config;

import java.time.Duration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.SerializationFeature;

@Configuration
public class BgcUiConfig {
	
	@Bean
    public Jackson2ObjectMapperBuilder jacksonBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.featuresToEnable(SerializationFeature.WRAP_ROOT_VALUE);       
        return builder;
    }
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
	    return builder
	            .setConnectTimeout(Duration.ofSeconds(180))
	            .setReadTimeout(Duration.ofSeconds(180))
	            .build();
	}

}
