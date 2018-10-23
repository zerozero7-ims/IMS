package com.ims.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ims.dao.IUserDAO;
import com.ims.entity.User;

@Controller
public class HelloController {
	@RequestMapping("/hello")
	public ModelAndView hello(){
		ModelAndView mv = new ModelAndView();
		mv.addObject("spring","spring mvc");
		mv.setViewName("hello");
		return mv;
		
	}




	
	@Autowired
	private IUserDAO userDAO;
	@RequestMapping("/user")
	public ModelAndView user(){
		
		User user = new User();
		user.setUsername("李俊贤");
		user.setPassword("123456");
		this.userDAO.insert(user);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("user",user);
		mv.setViewName("users");
		return mv;
		
	}
	@ResponseBody
	@RequestMapping("/loadinfo")
	public Map<String, Object> loadinfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/json;character=utf-8"); 
		response.setCharacterEncoding("utf-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Access-Control-Allow-Origin", "*"); 
	    
        String yhm = request.getParameter("userName");
		String mm = request.getParameter("password");
		System.out.println("用户名："+yhm);
		System.out.println("密码："+mm);
		
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("code", 1);
	    map.put("message", "登录提示");
	    map.put("userName", yhm);
	    map.put("password", mm);
	    
	    return map;
		
	}

}
