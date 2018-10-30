package com.ims.controller;

import com.ims.dao.EntMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller

public class IndexController {
	@Resource(name = "entMapper")
	private EntMapper _e;

	@RequestMapping("/main")
	public ModelAndView main(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("main");
		return mv;
	}

	@RequestMapping("/index")
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
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





















