package com.example.demo.api;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.demo.mapper.HelpMapper;
import com.example.demo.tables.Help;
import com.example.demo.tables.User;
import javax.annotation.Resource;
import java.util.List;
import com.example.demo.ReturnInfo;
import com.example.demo.Query;

@RestController
public class HelpController {
	
	@Resource
	private HelpMapper mapper;
	
	@PostMapping("getHelp")
	public ReturnInfo getSecond(@RequestBody Query query) {
		ReturnInfo res = new ReturnInfo();
		List<Help> allresult = mapper.getAll();
		List<Help> result = mapper.getAllByNum((query.getPagenum() - 1) * query.getPagesize(), query.getPagesize());
		if(result != null) {
			res.setTotalnum(allresult.size());
			res.setData(result);
			res.setStatus(200);
			res.setMessage("获取跑腿服务成功");
		}
		return res;
	}
	
	@PostMapping("postHelp")
	public ReturnInfo postSecond(@RequestBody Help help) {
		ReturnInfo res = new ReturnInfo();
		if(mapper.insert(help) == 1) {
			res.setStatus(200);
			res.setMessage("发布成功");
		}
		else res.setMessage("发布失败，请稍后再试");
		return res;
	}
	
	@PostMapping("getUserHelp")
	public ReturnInfo getUserSecond(@RequestBody User user) {
		ReturnInfo res = new ReturnInfo();
		List<Help> result = mapper.getAllByUsername(user.getUsername());
		if(result != null) {
			res.setData(result);
			res.setStatus(200);
			res.setMessage("获取成功");
		}
		else res.setMessage("获取失败，请稍后再试");
		return res;
	}
	
	@PostMapping("editHelp")
	public ReturnInfo editsecond(@RequestBody Help help) {
		ReturnInfo res = new ReturnInfo();
		if(mapper.edit(help) == 1) {
			res.setStatus(200);
			res.setMessage("修改成功");
		}
		else res.setMessage("修改失败，请稍后再试");
		return res;
	}
	
	@PostMapping("deleteHelp")
	public ReturnInfo deletesecond(@RequestBody Help help) {
		ReturnInfo res = new ReturnInfo();
		if(mapper.delete(help) == 1) {
			res.setStatus(200);
			res.setMessage("删除成功");
		}
		else res.setMessage("删除失败，请稍后再试");
		return res;
	}
	
	@GetMapping("getAllHelp")
	public ReturnInfo getAllHelp() {
		ReturnInfo res = new ReturnInfo();
		List<Help> result = mapper.getAll();
		if(result != null) {
			res.setData(result);
			res.setStatus(200);
			res.setMessage("获取跑腿服务成功");
		}
		return res;
	}
}
