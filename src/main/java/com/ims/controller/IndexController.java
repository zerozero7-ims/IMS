package com.ims.controller;

import com.ims.dao.IBuildingDAO;
import com.ims.dao.ICompanyDAO;
import com.ims.entity.Building;
import com.ims.entity.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller

public class IndexController {
	@Autowired
	private IBuildingDAO buildingDAO;
	@Autowired
	private ICompanyDAO companyDAO;

	@RequestMapping("/main")
	public ModelAndView main(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("main");
		return mv;
	}

	@RequestMapping("/users")
	public ModelAndView users(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("users");
		return mv;
	}
	@RequestMapping("/companys")
	public ModelAndView companys(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("companys");
		return mv;
	}

	@RequestMapping("/lease")
	public ModelAndView lease(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("lease");
		return mv;
	}

	@RequestMapping("/matching")
	public ModelAndView matching(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("matching");
		return mv;
	}

	@RequestMapping("/own")
	public ModelAndView own(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("own");
		return mv;
	}

	@RequestMapping("/lease_detail")
	public ModelAndView lease_detail(){
		List<Map<String, Object>> floorlist = new ArrayList<Map<String, Object>>();
		String[] floors = {"1","2","3","4","5","6","7"};
		for(String floor:floors){
			List<Map<String, Object>> combuildlist = new ArrayList<Map<String, Object>>();
			Map<String, Object> temp = new HashMap<String, Object>();
			float totalarea = buildingDAO.sumAreaByFloor(floor);
			if(totalarea>0){
				List<Building> buildingList = buildingDAO.findAllbyFloor(floor);
				for(Building building:buildingList){
					Map<String, Object> temp2 = new HashMap<String, Object>();
					Company company = companyDAO.findcurrByRoomnum(building.getRoomnum());
					temp2.put("building",building);
					temp2.put("company",company);
					combuildlist.add(temp2);
				}

				System.out.println("floor:"+floor+" -- "+totalarea);
				temp.put("floor",floor);
				temp.put("totalarea",totalarea);
				temp.put("combuildlist",combuildlist);
				floorlist.add(temp);
			}
		}
		Map<String, Object> map = new HashMap<>();
		map.put("floorlist",floorlist);
		return new ModelAndView("lease_detail",map);


	}
	@RequestMapping("/abc")
	@ResponseBody
	public Object abc(){
		List<Map<String, Object>> floorlist = new ArrayList<>();
		String[] floors = {"1","2","3","4","5","6","7"};
		for(String floor:floors){
			List<Map<String, Object>> combuildlist = new ArrayList<>();
			Map<String, Object> temp = new HashMap<String, Object>();
			float totalarea = buildingDAO.sumAreaByFloor(floor);
			if(totalarea>0){
				List<Building> buildingList = buildingDAO.findAllbyFloor(floor);
				for(Building building:buildingList){
					Map<String, Object> temp2 = new HashMap<String, Object>();
					Company company = companyDAO.findcurrByRoomnum(building.getRoomnum());
					temp2.put("building",building);
					temp2.put("company",company);
					combuildlist.add(temp2);
				}

				System.out.println("floor:"+floor+" -- "+totalarea);
				temp.put("floor",floor);
				temp.put("totalarea",totalarea);
				temp.put("combuildlist",combuildlist);
				floorlist.add(temp);
			}
		}
		Map<String, Object> map = new HashMap<>();
		map.put("floorlist",floorlist);
		return map;
	}







	@RequestMapping("/statistical_company")
	public ModelAndView statistical_company(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("statistical_company");
		return mv;
	}
		
}





















