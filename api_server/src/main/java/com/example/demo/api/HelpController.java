package com.example.demo.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.mapper.HelpMapper;
import com.example.demo.tables.Help;
import javax.annotation.Resource;
import java.util.List;

import com.example.demo.ReturnInfo;
import com.example.demo.JwtUtil;
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
}
