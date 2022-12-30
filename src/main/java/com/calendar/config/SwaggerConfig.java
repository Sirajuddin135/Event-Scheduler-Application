package com.calendar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {
	
	@Bean
	public OpenAPI springShopOpenAPI() {
		return new OpenAPI().info(new Info().title("Event Scheduler Application")
			.description("Backend APis for Event Scheduler app")
			.version("v1.0.0")
			.contact(new Contact().name("Shaik Sirajuddin").url("https://sirajuddin135.github.io/").email("shaiksirajuddin135@gmail.com"))
			.license(new License().name("License").url("https://sirajuddin135.github.io/")))
			.externalDocs(new ExternalDocumentation().description("Event Scheduler App Documentation")
			.url("http://localhost:8080/swagger-ui/index.html"));
	}
}
