package com.ims.controller;


import com.ims.dao.IMenuDAO;
import com.ims.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IncController {
    @Autowired
    private IMenuDAO menuDAO ;

    @RequestMapping("/inc/nav")
    public ModelAndView nav(){


        List<Menu> menuData = new ArrayList<Menu>();
        for(Menu menu:this.menuDAO.findByParentId(0)){
            menu.setChildMenus(getChild(menu.getMenuid()));
            menuData.add(menu);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("menuData",menuData);
        return new ModelAndView("inc/nav",map);

    }


    public List<Menu> getChild(int parentid){
        List<Menu> childList = new ArrayList<>();
        List<Menu> child = menuDAO.findByParentId(parentid);
        for(Menu menu:child){
            menu.setChildMenus(getChild(menu.getMenuid()));
            childList.add(menu);
        }
        if(childList.size() == 0){
            return null;
        }
        return childList;
    }

    @RequestMapping("/common/setting")
    public ModelAndView setting(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("spring","spring mvc");
        mv.setViewName("common/setting");
        return mv;

    }




}
