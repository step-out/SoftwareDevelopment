package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ApiServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiServerApplication.class, args);
	}

	@Configuration
	public class CrosConfig implements WebMvcConfigurer {
	    @Override
	    public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/**")
	                .allowedOrigins("*")
	                .allowedMethods("GET","HEAD","POST","DELETE","OPTIONS","PUT")
	                .maxAge(3600)
	                .allowedHeaders("*");
	    }
	}
}