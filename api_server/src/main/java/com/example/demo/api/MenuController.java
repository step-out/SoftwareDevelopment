package com.example.demo.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@GetMapping("/menu/getNanguang")
	public ReturnInfo getNanguang() {
		ReturnInfo res = new ReturnInfo();
		List<Menu> result = mapper.getMenuByBelong("南光");
		if(result != null) {
			res.setData(result);
			res.setStatus(200);
			res.setMessage("获取南光菜单成功");
		}
		return res;
	}
	
	@GetMapping("/menu/getFurong")
	public ReturnInfo getFurong() {
		ReturnInfo res = new ReturnInfo();
		List<Menu> result = mapper.getMenuByBelong("芙蓉");
		if(result != null) {
			res.setData(result);
			res.setStatus(200);
			res.setMessage("获取芙蓉菜单成功");
		}
		return res;
	}
	
	@GetMapping("/menu/getQinye")
	public ReturnInfo getQinye() {
		ReturnInfo res = new ReturnInfo();
		List<Menu> result = mapper.getMenuByBelong("勤业");
		if(result != null) {
			res.setData(result);
			res.setStatus(200);
			res.setMessage("获取勤业菜单成功");
		}
		return res;
	}
	
	@GetMapping("/menu/getDongyuan")
	public ReturnInfo getDongyuan() {
		ReturnInfo res = new ReturnInfo();
		List<Menu> result = mapper.getMenuByBelong("东苑");
		if(result != null) {
			res.setData(result);
			res.setStatus(200);
			res.setMessage("获取东苑菜单成功");
		}
		return res;
	}
	
	@GetMapping("/menu/getJingfeng")
	public ReturnInfo getJingfeng() {
		ReturnInfo res = new ReturnInfo();
		List<Menu> result = mapper.getMenuByBelong("竞丰");
		if(result != null) {
			res.setData(result);
			res.setStatus(200);
			res.setMessage("获取竞丰菜单成功");
		}
		return res;
	}
	
	@GetMapping("/menu/getFengting")
	public ReturnInfo getFengting() {
		ReturnInfo res = new ReturnInfo();
		List<Menu> result = mapper.getMenuByBelong("丰庭");
		if(result != null) {
			res.setData(result);
			res.setStatus(200);
			res.setMessage("获取丰庭菜单成功");
		}
		return res;
	}
	
	@GetMapping("/menu/getSiyuan")
	public ReturnInfo getSiyuan() {
		ReturnInfo res = new ReturnInfo();
		List<Menu> result = mapper.getMenuByBelong("思源");
		if(result != null) {
			res.setData(result);
			res.setStatus(200);
			res.setMessage("获取思源菜单成功");
		}
		return res;
	}
}
