package com.ims.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ims.util.Indexfunc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ims.dao.IMenuDAO;
import com.ims.dao.INewsDAO;
import com.ims.dao.IUserDAO;
import com.ims.dao.IWorksDAO;
import com.ims.entity.Menu;
import com.ims.entity.News;
import com.ims.entity.Works;

@RestController
public class IndexController11 {
	
	@Autowired
	private IUserDAO userDAO;
	
	@Autowired
	private INewsDAO newsDAO;
	
	@Autowired
	private IWorksDAO worksDAO ;
	
	@Autowired
	private IMenuDAO menuDAO ;
	
	@RequestMapping("/menu")
	@ResponseBody
	public Object menu(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/json;character=utf-8"); 
		response.setCharacterEncoding("utf-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Access-Control-Allow-Origin", "*");

        List<Menu> menuData = new ArrayList<Menu>();
		for(Menu menu:this.menuDAO.findByParentId(0)){
			menuData.add(menu);
		}
		return menuData;   //返回json格式的信息
	}


	
	@RequestMapping("/home")
	@ResponseBody
	public Object home(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/json;character=utf-8"); 
		response.setCharacterEncoding("utf-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Access-Control-Allow-Origin", "*");
        Map<String, Object> map=new HashMap<String,Object>();
        
        String yhm = request.getParameter("userName");
		String mm = request.getParameter("password");
		System.out.println("用户名："+yhm);
		System.out.println("密码："+mm);
		
		
		
		List<News> ywlist = new ArrayList<News>();
		ywlist = this.newsDAO.query("SELECT * FROM news where type=1 LIMIT 4 ");
		List<News> tzlist = new ArrayList<News>();
		tzlist = this.newsDAO.query("SELECT * FROM news where type=2 LIMIT 6 ");
		List<News> yblist = new ArrayList<News>();
		yblist = this.newsDAO.query("SELECT * FROM news where type=3 LIMIT 6 ");
		
		
		

		map.put("ywlist", ywlist);
		map.put("tzlist", tzlist);
		map.put("yblist", yblist);
		
		return map;   //返回json格式的信息
	}
	
	@RequestMapping("/news")
	@ResponseBody
	public Object news(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/json;character=utf-8"); 
		response.setCharacterEncoding("utf-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Access-Control-Allow-Origin", "*");   
        String mode = request.getParameter("mode");
		String page = request.getParameter("page");
		
		int pageSize = 10;
		int current = 1;
		if(!"".equals(page) && page!=null){
			current = Integer.parseInt(page);
		}
		int type = 0;
		if("focus".equals(mode)){
			type = 1;
		}else if("notice".equals(mode)){
			type = 2;
		}else if("trends".equals(mode)){
			type = 3;
		}
		
		System.out.println("模块："+mode);
		System.out.println("页码："+page);
		System.out.println("当前页："+current);
		
		
		List<News> newslist = new ArrayList<News>();
		newslist = this.newsDAO.query("SELECT * FROM news where type="+type+" LIMIT "+(current-1)*pageSize+","+pageSize);
		int total = this.newsDAO.total(type);
		
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("newslist", newslist);
		map.put("total", total);
		System.out.println("总记录："+total);
		
		return map;   //返回json格式的信息
	}
	
	
	
	@RequestMapping("/works")
	@ResponseBody
	public Object works(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/json;character=utf-8"); 
		response.setCharacterEncoding("utf-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Access-Control-Allow-Origin", "*");	
		List<Works> workslist = new ArrayList<Works>();
		workslist = this.worksDAO.findAll();
		return workslist;   //返回json格式的信息
	}
		
}





















