package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.tables.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import java.util.List;

@Mapper
public interface MenuMapper extends BaseMapper<Menu>{
	@Select("SELECT * from menu")
	List<Menu> getAllMenu();

	@Insert("INSERT into menu (menu, belong) values (#{menu}, #{belong})")
	int insert(Menu menu);
	
	@Delete("DELETE from menu WHERE id = #{id}")
	int delete(Menu menu);
}
