package com.bgc.backgroundVerification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@EnableAutoConfiguration
@PropertySources({
	@PropertySource(ignoreResourceNotFound=true, value="classpath:bgcui.properties")
})
public class BgcUiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BgcUiApplication.class, args);
	}

}
