package com.ims.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ims.dao.EntMapper;
import com.ims.entity.*;
import com.ims.util.Common;
import com.ims.util.JWT;
import com.ims.util.ResponseData;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.LoggerFactory;
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










    @RequestMapping("login")
    public ModelAndView toLogin() {
		return new ModelAndView("login");
    }

    @RequestMapping(value = "login1",method = RequestMethod.POST)
    public ModelAndView shirologin1(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Map<String,Object> map = new HashMap<String, Object>();
        System.out.println("shiro登录");
        Login login = new Login();
        login.setUsername(username);
        login.setPassword(password);
        Integer uid = userDAO.checkLogin(username,password);
        if(null != uid){
			try {
				User user = userDAO.findById(uid);
				login.setUid(uid);
				String token = new JWT().sign(login,60L* 1000L* 30L);

				UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(token, token);
				Subject subject = SecurityUtils.getSubject();
				subject.login(usernamePasswordToken);
				map.put("user",user);
				map.put("token",token);
				response.addHeader("Authorization",token);
				return new ModelAndView("main",map);

			} catch (Exception e) {
				map.put("error",e.getMessage());
				return new ModelAndView("login",map);
			}

		}else{
        	map.put("error","用户名或密码错误！");
			return new ModelAndView("login",map);
		}


    }

	@RequestMapping(value = "login",method = RequestMethod.POST)
	public ResponseData shirologin(String username, String password) {
		System.out.println("shiro登录");
		Login login = new Login();
		login.setUsername(username);
		login.setPassword(password);
		ResponseData responseData = ResponseData.ok();
		Integer uid = userDAO.checkLogin(username,password);
		System.out.println("-------------"+uid);
		if(null != uid){
			try {
				User user = userDAO.findById(uid);
				login.setUid(uid);

				System.out.println("id:"+login.getUid());
				System.out.println("username:"+login.getUsername());
				System.out.println("password:"+login.getPassword());
				String token = new JWT().sign(login,60L* 1000L* 30L);
				responseData.putDataValue("uid",login.getUid());
				responseData.putDataValue("token",token);
				responseData.putDataValue("user",user);
				UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(token, token);
				Subject subject = SecurityUtils.getSubject();
				subject.login(usernamePasswordToken);
				responseData.putDataValue("success","true");
			} catch (Exception e) {
				responseData.putDataValue("error",e.getMessage());
			}

		}else{
			responseData = ResponseData.customerError();
		}
		return responseData;

	}

    @RequestMapping("user")
    public String toUser(){
        System.out.println("进入 user");
        return "welcome to user";
    }

    @RequestMapping("admin")
    public String toAdmin(){
        return "welcome to admin";
    }

    @RequestMapping("unauthorized")
    public String unAuth(){
        return "unauthorized";
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





















