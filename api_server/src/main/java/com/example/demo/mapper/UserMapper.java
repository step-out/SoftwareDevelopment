package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.tables.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Insert;

@Mapper
public interface UserMapper extends BaseMapper<User>{
	@Select("SELECT * from user where username = #{username} and password = #{password}")
	User getUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
	
	@Select("SELECT * from user where username = #{username}")
	User getUserByUsername(@Param("username") String username);
	
	@Insert("INSERT into user (username, password, phone) values (#{username}, #{password}, #{phone})")
	int insertUser(User user);
}
