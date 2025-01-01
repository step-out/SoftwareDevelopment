package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.tables.Help;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Insert;

import java.util.List;

@Mapper
public interface HelpMapper extends BaseMapper<Help>{
	@Select("SELECT * from help")
	List<Help> getAll();
	
	@Select("SELECT * from help order by id desc limit #{start}, #{pagesize}")
	List<Help> getAllByNum(@Param("start") Integer start, @Param("pagesize") Integer pagesize);
	
//	@Select("SELECT * from admin where adminname = #{adminname} and password = #{password}")
//	User getAdminByAdminnameAndPassword(@Param("adminname") String adminname, @Param("password") String password);
//	
//	@Select("SELECT * from user where phone = #{phone}")
//	User getUserByPhone(@Param("phone") String phone);
//	
//	@Select("SELECT * from user where username = #{username}")
//	User getUserByUsername(@Param("username") String username);
//	
	@Insert("INSERT into help (need, user) values (#{need}, #{user})")
	int insert(Help help);
}
