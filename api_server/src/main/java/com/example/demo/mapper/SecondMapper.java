package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.tables.Second;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Delete;
import java.util.List;

@Mapper
public interface SecondMapper extends BaseMapper<Second>{
	@Select("SELECT * from second")
	List<Second> getAll();
	
	@Select("SELECT * from second order by id desc limit #{start}, #{pagesize}")
	List<Second> getAllByNum(@Param("start") Integer start, @Param("pagesize") Integer pagesize);

	@Select("SELECT * from second where user = #{username}")
	List<Second> getAllByUsername(@Param("username") String username);

	@Insert("INSERT into second (goods, user) values (#{goods}, #{user})")
	int insert(Second second);
	
	@Update("UPDATE second set goods = #{goods} WHERE id = #{id}")
	int edit(Second second);
	
	@Delete("DELETE from second WHERE id = #{id}")
	int delete(Second second);
}
