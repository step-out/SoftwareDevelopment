package com.example.demo.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.mapper.UserMapper;
import com.example.demo.tables.User;
import javax.annotation.Resource;

import com.example.demo.ReturnInfo;
import com.example.demo.JwtUtil;

@RestController
// @RequestMapping("/login")
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
	
	// 注册的处理函数
	@PostMapping("/reguser")
	public ReturnInfo reguser(@RequestBody User user) {
		ReturnInfo res = new ReturnInfo();
		User result = mapper.getUserByUsername(user.getUsername());
		if(result != null) {
			res.setMessage("用户名被占用，请更换其他用户名");
		}
		else {
			if(mapper.insertUser(user) == 1) {
				res.setStatus(200);
				res.setMessage("注册成功");
			}
			else res.setMessage("注册用户失败，请稍后再试");
		}
		return res;
	}
}
