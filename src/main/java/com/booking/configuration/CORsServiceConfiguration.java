package com.booking.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CORsServiceConfiguration {
	
	protected static final String[] METHODS = {"GET","PUT","POST","DELETE","OPTIONS"};
	
	protected static final String[] ALLOWED_ORIGINS = {"http://localhost:8080","http://localhost:4200",
			"https://ecc9-103-98-209-72.ngrok-free.app",
			"https://d658-103-98-209-72.ngrok-free.app",
			"tcp://0.tcp.in.ngrok.io:12610"};
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
					.allowedMethods(METHODS)
					.allowedOrigins("*");
			}
		};
	}

}
