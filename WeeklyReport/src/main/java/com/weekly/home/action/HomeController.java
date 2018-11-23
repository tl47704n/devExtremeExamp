package com.weekly.home.action;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.weekly.home.repository.HomeRepository;

@Controller
public class HomeController {
	@Autowired
	HomeRepository homeRepository;
	@Autowired
	ServletContext servletContext;
	@Autowired
	ExcelReader excelReader;
	@RequestMapping("/home")
	public String gotopage() {
		return "/templates/home";
	}
	
	@RequestMapping(value = "/getSRdata", method = RequestMethod.POST)
	@ResponseBody
	public JSONArray getSRdata(@RequestBody JSONObject data){
		 //String path = servletContext.getRealPath();
		// System.out.println("classpath:static/files/Manday Detail.xlsx");
		return excelReader.getexcel("src/main/resources/static/files/Manday Detail.xlsx");
	}
}
