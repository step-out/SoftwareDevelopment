package com.example.demo.tables;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;

@TableName("help")
public class Help {
	@TableId(type=IdType.AUTO)
	private Integer id;
	private String need;
	private String user;
	
	public String getNeed() {
		return need;
	}
	public String getUser() {
		return user;
	}
}
