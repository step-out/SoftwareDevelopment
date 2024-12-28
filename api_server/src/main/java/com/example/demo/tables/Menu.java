package com.example.demo.tables;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;

@TableName("menu")
public class Menu {
	@TableId(type=IdType.AUTO)
	private Integer id;
	private String menu;
	private String belong;
	
	public String getMenu() {
		return menu;
	}
	public String getBelong() {
		return belong;
	}
}
