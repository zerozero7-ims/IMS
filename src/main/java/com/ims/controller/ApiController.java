package com.ims.controller;

import java.io.File;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.ims.dao.*;
import com.ims.entity.*;
import com.ims.util.JWT;
import com.ims.util.ResponseData;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ApiController {



	@Autowired
	private IUserDAO userDAO;
	@Autowired
	private ICompanyDAO companyDAO;
	@Autowired
	private IBuildingDAO buildingDAO;
	@Autowired
	private IPaymentDAO paymentDAO;
	@Autowired
	private IAttachmentDAO attachmentDAO;

	@RequestMapping("/useraction")
	@ResponseBody
	public Object useraction(@RequestParam("action") String action, @RequestParam("users") String userlist){
       List<User> users = new ArrayList<User>();
       users = JSONArray.parseArray(userlist,User.class);
       List<User> ulist = new ArrayList<>();
	    for(User user:users) {
	        if("edit".equals(action)){
                userDAO.update(user);
                ulist.add(user);
            }else if("create".equals(action)){
	            userDAO.insert(user);
                ulist.add(user);
            }else if("remove".equals(action)){
	            userDAO.delete(user.getId());
            }

        }

        Map<String, Object> map = new HashMap<String, Object>();
	    map.put("data", ulist);
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

	@RequestMapping("/companyaction")
	@ResponseBody
	public Object companyaction(@RequestParam("action") String action, @RequestParam("companys") String companylist, @RequestParam(value="upload",required =false) MultipartFile file){

		Map<String, Object> map = new HashMap<String, Object>();

		List<Company> companys = new ArrayList<Company>();
		companys = JSONArray.parseArray(companylist,Company.class);
		List<Company> comlist = new ArrayList<>();
		for(Company company:companys) {
			if("edit".equals(action)){
				companyDAO.update(company);
				for(Payment payment:company.getPaystatus()){
					if(payment.getId()==0){
						payment.setCompanyid(company.getId());
						paymentDAO.insert(payment);
					}else{
						paymentDAO.update(payment);
					}
				}
				comlist.add(company);
			}else if("create".equals(action)){
				companyDAO.insert(company);
				for(Payment payment:company.getPaystatus()){
					payment.setCompanyid(company.getId());
					paymentDAO.insert(payment);
				}
				comlist.add(company);
			}else if("remove".equals(action)){
				for(Payment payment:company.getPaystatus()){
					paymentDAO.deletebyCid(company.getId());
				}
				companyDAO.delete(company.getId());
			}

		}

		map.put("data", comlist);
//文件上传
		String path = "F:/ideaworkspace/hfqxm/IMS/src/main/webapp/assets/upload/";
		if(file!=null && !file.isEmpty()){
			Map<String, Object> f = new HashMap<String, Object>();
			System.out.println(file.getOriginalFilename());
			System.out.println(file.getName());
			System.out.println(file.getSize());
			f.put("filename",file.getOriginalFilename());
			f.put("filesize",file.getSize());
			f.put("id",1);
			f.put("system_path",path+file.getOriginalFilename());
			f.put("web_path","assets/upload/"+file.getOriginalFilename());
			//获取原始文件名
			String fileName = file.getOriginalFilename();
			//获取文件类型
			String fileType = fileName.substring(fileName.lastIndexOf("."), fileName.length());
			//获取当前时间戳作为文件名，防止上传的文件名出现重名而被覆盖的现象。
			fileName = new Date().getTime()+fileType;
			Attachment attachment = new Attachment();
			attachment.setFilename(fileName);
			attachment.setFilesize(file.getSize());
			attachment.setSystem_path(path+fileName);
			attachment.setWeb_path("assets/upload/"+fileName);
			attachmentDAO.insert(attachment);
			saveFile(file,path,fileName);

			Map<String, Object> fils = new HashMap<String, Object>();
			fils.put(String.valueOf(attachment.getId()),attachment);
			Map<String, Object> fi = new HashMap<String, Object>();
			fi.put("files",fils);
			map.put("files",fi);
			Map<String, Object> u = new HashMap<String, Object>();
			u.put("id",attachment.getId());
			map.put("upload",u);

		}



		return map;   //返回json格式的信息
	}


	@RequestMapping("/companylist")
	@ResponseBody
	public Object companylist(){
		List<Company> comlist = new ArrayList<>();
		for(Company company:companyDAO.findAll()){
			company.setPaystatus(paymentDAO.selectbyCid(company.getId()));
			comlist.add(company);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", comlist);
		Map<String, Object> fils = new HashMap<String, Object>();
		for(Attachment attachment:attachmentDAO.findAll()) {
			fils.put(String.valueOf(attachment.getId()), attachment);
		}
		Map<String, Object> fi = new HashMap<String, Object>();
		fi.put("files",fils);
		map.put("files",fi);

		return map;
	}


	@RequestMapping("/leaseaction")
	@ResponseBody
	public Object leaseaction(@RequestParam("action") String action, @RequestParam("leases") String leaselist){
		List<Map<String, Object>> bclist = new ArrayList<>();
		List<Building> leases = new ArrayList<Building>();
		leases = JSONArray.parseArray(leaselist,Building.class);
		for(Building lease:leases) {
			Map<String, Object> temp = new HashMap<String, Object>();
			if("edit".equals(action)){
				lease.setBuildingtype(0);
				buildingDAO.update(lease);
				temp.put("lease",lease);
				bclist.add(temp);
			}else if("create".equals(action)){
				lease.setBuildingtype(0);
				buildingDAO.insert(lease);
				temp.put("lease",lease);
				bclist.add(temp);
			}else if("remove".equals(action)){
				buildingDAO.delete(lease.getId());
			}

		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", bclist);
		return map;   //返回json格式的信息
	}

	@RequestMapping("/leaselist")
	@ResponseBody
	public Object leaselist(){
		List<Map<String, Object>> bclist = new ArrayList<>();
		List<Building> leases = new ArrayList<Building>();
		leases = buildingDAO.findAllbyBuildingtype(0);
		for(Building lease:leases){
			Map<String, Object> temp = new HashMap<String, Object>();
			Company company = companyDAO.findcurrByRoomnum(lease.getRoomnum());
			temp.put("lease",lease);
			temp.put("company",company);

			bclist.add(temp);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", bclist);
		return map;
	}

	@RequestMapping("/matchingaction")
	@ResponseBody
	public Object matchingaction(@RequestParam("action") String action, @RequestParam("matchings") String matchinglist){
		List<Map<String, Object>> malist = new ArrayList<>();
		List<Building> matchings = new ArrayList<Building>();
		matchings = JSONArray.parseArray(matchinglist,Building.class);
		for(Building matching:matchings) {
			Map<String, Object> temp = new HashMap<String, Object>();
			if("edit".equals(action)){
				matching.setBuildingtype(2);
				buildingDAO.update(matching);
				temp.put("matching",matching);
				malist.add(temp);
			}else if("create".equals(action)){
				matching.setBuildingtype(2);
				buildingDAO.insert(matching);
				temp.put("matching",matching);
				malist.add(temp);
			}else if("remove".equals(action)){
				buildingDAO.delete(matching.getId());
			}

		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", malist);
		return map;   //返回json格式的信息
	}

	@RequestMapping("/matchinglist")
	@ResponseBody
	public Object matchinglist(){
		List<Map<String, Object>> bclist = new ArrayList<>();
		List<Building> matchings = new ArrayList<Building>();
		matchings = buildingDAO.findAllbyBuildingtype(2);
		for(Building matching:matchings){
			Map<String, Object> temp = new HashMap<String, Object>();
			Company company = companyDAO.findcurrByRoomnum(matching.getRoomnum());
			temp.put("matching",matching);
			temp.put("company",company);

			bclist.add(temp);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", bclist);
		return map;
	}

	@RequestMapping("/ownaction")
	@ResponseBody
	public Object ownaction(@RequestParam("action") String action, @RequestParam("owns") String ownlist){
		List<Building> owns = new ArrayList<Building>();
		owns = JSONArray.parseArray(ownlist,Building.class);
		List<Building> olist = new ArrayList<>();
		for(Building own:owns) {
			if("edit".equals(action)){
				own.setBuildingtype(1);
				buildingDAO.update(own);
				olist.add(own);
			}else if("create".equals(action)){
				own.setBuildingtype(1);
				buildingDAO.insert(own);
				olist.add(own);
			}else if("remove".equals(action)){
				buildingDAO.delete(own.getId());
			}

		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", olist);
		return map;   //返回json格式的信息
	}


	@RequestMapping("/ownlist")
	@ResponseBody
	public Object ownlist(){
		List<Building> ownlist = new ArrayList<Building>();
		ownlist = buildingDAO.findAllbyBuildingtype(1);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", ownlist);
		return map;
	}

	@RequestMapping("/filesUpload")
	@ResponseBody
	public Object filesUpload(@RequestParam("upload") MultipartFile[] files){
		String path = "E:/upload/";
		if(files!=null && files.length>0){
			for(int i=0; i<files.length; i++){
				MultipartFile file = files[i];
				System.out.println(file.getOriginalFilename());
				System.out.println(file.getName());
				System.out.println(file.getSize());
				saveFile(file,path,file.getOriginalFilename());
			}
		}




		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("data", ownlist);
		return map;
	}


	private boolean saveFile(MultipartFile file, String path, String fileName) {
		// 判断文件是否为空
		if (!file.isEmpty()) {
			try {
				File filepath = new File(path);
				if (!filepath.exists())
					filepath.mkdirs();
				// 文件保存路径
				String savePath = path + fileName;
				// 转存文件
				file.transferTo(new File(savePath));
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}








	@RequestMapping("login")
    public ModelAndView toLogin() {
		return new ModelAndView("login");
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





















