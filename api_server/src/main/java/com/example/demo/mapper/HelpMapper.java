package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.tables.Help;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import java.util.List;

@Mapper
public interface HelpMapper extends BaseMapper<Help>{
	@Select("SELECT * from help")
	List<Help> getAll();
	
	@Select("SELECT * from help order by id desc limit #{start}, #{pagesize}")
	List<Help> getAllByNum(@Param("start") Integer start, @Param("pagesize") Integer pagesize);
	
	@Select("SELECT * from help where user = #{username}")
	List<Help> getAllByUsername(@Param("username") String username);

	@Insert("INSERT into help (need, user) values (#{need}, #{user})")
	int insert(Help help);
	
	@Update("UPDATE help set need = #{need} WHERE id = #{id}")
	int edit(Help help);
	
	@Delete("DELETE from help WHERE id = #{id}")
	int delete(Help help);
}
