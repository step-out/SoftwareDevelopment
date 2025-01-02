package com.example.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;

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
