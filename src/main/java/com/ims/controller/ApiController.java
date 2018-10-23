package com.ims.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ims.dao.EntMapper;
import com.ims.entity.Menu;
import com.ims.util.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ims.dao.INewsDAO;
import com.ims.dao.IUserDAO;
import com.ims.dao.IWorksDAO;
import com.ims.entity.News;
import com.ims.entity.User;
import com.ims.entity.Works;
import com.ims.util.Crawler;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ApiController {

	@Autowired
	private IUserDAO userDAO;

	@Autowired
	private INewsDAO newsDAO;

	@Autowired
	private IWorksDAO worksDAO;
	@Resource(name = "entMapper")
	private EntMapper _e;

	@RequestMapping("/updateuser")
	@ResponseBody
	public Object updateuser(@RequestBody User user){
		System.out.println("-------------------------");
		System.out.println("用户名："+user.getUsername());
		System.out.println("密码："+user.getPassword());
		System.out.println("用户类型："+user.getUsertype());
		System.out.println("-------------------------");

		Map<String, String> map=new HashMap<String,String>();
		map.put("status", "ok");
		map.put("currentAuthority", "admin");


		return map;   //返回json格式的信息
	}
	@RequestMapping("/userlist")
	@ResponseBody
	public Object userlist(){
		List<User> ulist = new ArrayList<>();
		ulist = userDAO.findAll();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", ulist);
		return map;
	}











	@RequestMapping(value="/login", method={RequestMethod.POST})
	@ResponseBody
	public Object login(HttpServletRequest request, HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		String yhm = request.getParameter("userName");
		String mm = request.getParameter("password");
		System.out.println("用户名："+yhm);
		System.out.println("密码："+mm);
		Map<String,String> info = new HashMap<String, String>();
		info.put("code", "1");
		info.put("message", "登录提示");
		

		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		request = ((ServletRequestAttributes) ra).getRequest();
		Object attribute = request.getSession().getAttribute("info");
		if(attribute == null){
			request.getSession().setAttribute("info", info);
		}
		try {
			response.getWriter().write(attribute.toString());;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return attribute;   //返回json格式的信息
		
	}
	@RequestMapping("/loginaction")
	public void loginaction(final HttpServletRequest request, ModelMap modelMap,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		String yhm = request.getParameter("userName");
		String mm = request.getParameter("password");
		System.out.println("用户名："+yhm);
		System.out.println("密码："+mm);
		
		

		List<User> users = new ArrayList<User>();
		users = this.userDAO.findAll();
		modelMap.put("data", users);
		modelMap.put("code", 1);
		modelMap.put("message", "登录提示");
		
		
	}

	@RequestMapping("/api/login/account1")
	@ResponseBody
	public Object account1(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/json;character=utf-8"); 
		response.setCharacterEncoding("utf-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Access-Control-Allow-Origin", "*");
        
        String yhm = request.getParameter("userName");
		String mm = request.getParameter("password");
		String params = request.getParameter("params");
		System.out.println("params："+params);
		System.out.println("用户名："+yhm);
		System.out.println("密码："+mm);
		
		List<News> newslist = new ArrayList<News>();
		newslist = this.newsDAO.findAll();

		
		return newslist;   //返回json格式的信息
	}
	
	@RequestMapping("/api/login/account")
	@ResponseBody
	public Object account(@RequestBody User user){
		
		System.out.println("用户名："+user.getUsername());
		
		Map<String, String> map=new HashMap<String,String>();
		map.put("status", "ok");
		map.put("currentAuthority", "admin");

		
		return map;   //返回json格式的信息
	}
	
	@RequestMapping("/api/currentUser")
	@ResponseBody
	public Object currentUser(){
		
		
		Map<String, String> map=new HashMap<String,String>();
		map.put("name", "yang ran ran");
		map.put("avater", "https://gw.alipayobjects.com/zos/rmsportal/BiazfanxmamNRoxxVxka.png");
		map.put("userid", "00000001");
		map.put("notifyCount", "12");

		
		return map;   //返回json格式的信息
	}


}





















