package com.example.demo.api;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.demo.mapper.MenuMapper;
import com.example.demo.tables.Menu;
import javax.annotation.Resource;
import com.example.demo.ReturnInfo;
import java.util.List;

@RestController
public class MenuController {
	
	@Resource
	private MenuMapper mapper;
	
	@GetMapping("/menu/getAll")
	public ReturnInfo getAll() {
		ReturnInfo res = new ReturnInfo();
		List<Menu> result = mapper.getAllMenu();
		if(result != null) {
			res.setData(result);
			res.setStatus(200);
			res.setMessage("获取菜单成功");
		}
		return res;
	}
	
	@PostMapping("/menu/add")
	public ReturnInfo add(@RequestBody Menu menu) {
		ReturnInfo res = new ReturnInfo();
		if(mapper.insert(menu) == 1) {
			res.setStatus(200);
			res.setMessage("添加菜单成功");
		}
		return res;
	}
	
	@PostMapping("/menu/deletemenu")
	public ReturnInfo deletemenu(@RequestBody Menu menu) {
		ReturnInfo res = new ReturnInfo();
		if(mapper.delete(menu) == 1) {
			res.setStatus(200);
			res.setMessage("删除菜单成功");
		}
		return res;
	}
}
