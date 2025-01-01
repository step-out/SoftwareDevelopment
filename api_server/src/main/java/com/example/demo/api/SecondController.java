package com.example.demo.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.mapper.SecondMapper;
import com.example.demo.tables.Second;
import com.example.demo.tables.User;
import javax.annotation.Resource;
import java.util.List;

import com.example.demo.ReturnInfo;
import com.example.demo.JwtUtil;
import com.example.demo.Query;

@RestController
public class SecondController {
	
	@Resource
	private SecondMapper mapper;
	
	@PostMapping("getSecond")
	public ReturnInfo getSecond(@RequestBody Query query) {
		ReturnInfo res = new ReturnInfo();
		List<Second> allresult = mapper.getAll();
		List<Second> result = mapper.getAllByNum((query.getPagenum() - 1) * query.getPagesize(), query.getPagesize());
		if(result != null) {
			res.setTotalnum(allresult.size());
			res.setData(result);
			res.setStatus(200);
			res.setMessage("获取二手交易成功");
		}
		return res;
	}
	
	@PostMapping("postSecond")
	public ReturnInfo postSecond(@RequestBody Second second) {
		ReturnInfo res = new ReturnInfo();
		if(mapper.insert(second) == 1) {
			res.setStatus(200);
			res.setMessage("发布成功");
		}
		else res.setMessage("发布失败，请稍后再试");
		return res;
	}
	
	@PostMapping("getUserSecond")
	public ReturnInfo getUserSecond(@RequestBody User user) {
		ReturnInfo res = new ReturnInfo();
		List<Second> result = mapper.getAllByUsername(user.getUsername());
		if(result != null) {
			res.setData(result);
			res.setStatus(200);
			res.setMessage("获取成功");
		}
		else res.setMessage("获取失败，请稍后再试");
		return res;
	}
	
	@PostMapping("editsecond")
	public ReturnInfo editsecond(@RequestBody Second second) {
		ReturnInfo res = new ReturnInfo();
		if(mapper.edit(second) == 1) {
			res.setStatus(200);
			res.setMessage("修改成功");
		}
		else res.setMessage("修改失败，请稍后再试");
		return res;
	}
	
	@PostMapping("deletesecond")
	public ReturnInfo deletesecond(@RequestBody Second second) {
		ReturnInfo res = new ReturnInfo();
		if(mapper.delete(second) == 1) {
			res.setStatus(200);
			res.setMessage("删除成功");
		}
		else res.setMessage("删除失败，请稍后再试");
		return res;
	}
}
