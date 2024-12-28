package com.example.demo.tables;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;

@TableName("user")
public class User {
	@TableId(type=IdType.AUTO)
	private Integer id;
	private String username;
	private String password;
	private String phone;
	private String email;
	private String picture;
	
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getPhone() {
		return phone;
	}
	public String getEmail() {
		return email;
	}
	public String getPicture() {
		return picture;
	}
}
