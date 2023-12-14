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
	
	protected static final String[] ALLOWED_ORIGINS = {"http://localhost:8080","http://localhost:4200"};
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
					.allowedMethods(METHODS)
					.allowedOrigins(ALLOWED_ORIGINS);
			}
		};
	}

}
