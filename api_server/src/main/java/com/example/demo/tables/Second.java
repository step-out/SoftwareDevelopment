package com.example.demo.tables;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;

@TableName("second")
public class Second {
	@TableId(type=IdType.AUTO)
	private Integer id;
	private String goods;
	private String user;
	
	public Integer getId() {
		return id;
	}
	public String getGoods() {
		return goods;
	}
	public String getUser() {
		return user;
	}
}
