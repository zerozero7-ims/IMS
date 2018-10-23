package com.ims.controller;

import com.ims.dao.EntMapper;
import com.ims.util.Common;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller

public class IndexController {
	@Resource(name = "entMapper")
	private EntMapper _e;

	@RequestMapping("/search")
	public ModelAndView search(){

		Map<String, Object> map = new HashMap<>();

		String types = "SELECT * FROM dict t where t.pid=(select id from dict where name='档案类型') order by id";
		String years = "select makedate from filelist group by makedate order by makedate desc";
		map.put("years",_e.r(years));
		map.put("types",_e.r(types));
		return new ModelAndView("search/search", map);
	}

	@RequestMapping("/index")
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		return mv;
	}

	@RequestMapping("/users")
	public ModelAndView users(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("users");
		return mv;
	}

	@RequestMapping("/statistical_company")
	public ModelAndView statistical_company(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("statistical_company");
		return mv;
	}
		
}





















