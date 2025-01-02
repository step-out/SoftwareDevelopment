package com.example.demo.api;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.demo.mapper.UserMapper;
import com.example.demo.tables.User;
import javax.annotation.Resource;
import java.util.List;
import java.util.ArrayList;
import com.example.demo.ReturnInfo;
import com.example.demo.JwtUtil;

@RestController
public class UserController {
	
	@Resource
	private UserMapper mapper;
	
	// 登录的处理函数
	@PostMapping("/login")
	public ReturnInfo login(@RequestBody User user) {
		ReturnInfo res = new ReturnInfo();
		User result = mapper.getUserByUsernameAndPassword(user.getUsername(), user.getPassword());
		if(result != null) {
			String token = "Bearer" + JwtUtil.createToken(result);  // 生成Token字符串
			res.setToken(token);
			res.setStatus(200);
			res.setMessage("登录成功");
		}
		else {
			res.setMessage("用户名或密码错误");
		}
		return res;
	}
	
	@PostMapping("/login_admin")
	public ReturnInfo login_admin(@RequestBody User user) {
		ReturnInfo res = new ReturnInfo();
		User result = mapper.getAdminByAdminnameAndPassword(user.getUsername(), user.getPassword());
		if(result != null) {
			String token = "Bearer" + JwtUtil.createToken(result);  // 生成Token字符串
			res.setToken(token);
			res.setStatus(200);
			res.setMessage("登录成功");
		}
		else {
			res.setMessage("用户名或密码错误");
		}
		return res;
	}
	
	// 注册的处理函数
	@PostMapping("/reguser")
	public ReturnInfo reguser(@RequestBody User user) {
		ReturnInfo res = new ReturnInfo();
		User result = mapper.getUserByPhone(user.getPhone());
		if(result != null) {
			res.setMessage("手机号已注册，请更换其他手机号");
		}
		else {
			result = mapper.getUserByUsername(user.getUsername());
			if(result != null) {
				res.setMessage("用户名已注册，请更换其他用户名");
			}
			else {
				if(mapper.insertUser(user) == 1) {
					res.setStatus(200);
					res.setMessage("注册成功");
				}
				else res.setMessage("注册用户失败，请稍后再试");
			}
		}
		return res;
	}
	
	@PostMapping("/userinfo")
	public ReturnInfo userinfo(@RequestBody User user) {
		ReturnInfo res = new ReturnInfo();
		User result = mapper.getUserByUsername(user.getUsername());
		List<User> result_list = new ArrayList<>();
		result_list.add(result);
		if(result != null) {
			res.setData(result_list);
			res.setStatus(200);
			res.setMessage("获取成功");
		}
		else {
			res.setMessage("获取失败");
		}
		return res;
	}
	
	@PostMapping("/edituser")
	public ReturnInfo edituser(@RequestBody List<User> info) {
		ReturnInfo res = new ReturnInfo();
		User old_info = info.get(0);
		User new_info = info.get(1);
		if(!new_info.getUsername().equals(old_info.getUsername())) {
			User result = mapper.getUserByUsername(new_info.getUsername());
			if(result != null) {
				res.setMessage("用户名已注册，请更换其他用户名");
				return res;
			}
		}
		if(!new_info.getPhone().equals(old_info.getPhone())) {
			User result = mapper.getUserByPhone(new_info.getPhone());
			if(result != null) {
				res.setMessage("手机号已注册，请更换其他手机号");
				return res;
			}
		}
		if(mapper.updateUser(new_info) == 1) {
			res.setStatus(200);
			res.setMessage("修改成功");
		}
		else {
			res.setMessage("修改失败");
		}
		return res;
	}
	
	@GetMapping("/getusers")
	public ReturnInfo getusers() {
		ReturnInfo res = new ReturnInfo();
		List<User> result = mapper.getAllUser();
		if(result != null) {
			res.setData(result);
			res.setStatus(200);
			res.setMessage("获取成功");
		}
		else {
			res.setMessage("获取失败");
		}
		return res;
	}
	
	@PostMapping("/deleteuser")
	public ReturnInfo deleteuser(@RequestBody User user) {
		ReturnInfo res = new ReturnInfo();
		if(mapper.delete(user) == 1) {
			res.setStatus(200);
			res.setMessage("删除成功");
		}
		else {
			res.setMessage("删除失败");
		}
		return res;
	}
}
