package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.tables.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Insert;

import java.util.List;

@Mapper
public interface MenuMapper extends BaseMapper<Menu>{
	@Select("SELECT * from menu where belong = #{belong}")
	List<Menu> getMenuByBelong(@Param("belong") String belong);
	
//	@Select("SELECT * from user where username = #{username}")
//	User getUserByUsername(@Param("username") String username);
//	
//	@Insert("INSERT into user (username, password, phone) values (#{username}, #{password}, #{phone})")
//	int insertUser(User user);
}
