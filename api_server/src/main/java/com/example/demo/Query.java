package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Query {
	private Integer pagenum;
	private Integer pagesize;
	
	public Integer getPagenum() {
		return pagenum;
	}
	public Integer getPagesize() {
		return pagesize;
	}
}