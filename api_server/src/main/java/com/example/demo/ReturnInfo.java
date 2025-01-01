package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ReturnInfo {
	private Integer status;
	private String message;
	private String token;
	private List data;
	private Integer totalnum;
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public void setData(List data) {
		this.data = data;
	}
	public void setTotalnum(Integer totalnum) {
		this.totalnum = totalnum;
	}
	public Integer getStatus() {
		return status;
	}
	public String getMessage() {
		return message;
	}
	public String getToken() {
		return token;
	}
	public List getData() {
		return data;
	}
	public Integer getTotalnum() {
		return totalnum;
	}
}
