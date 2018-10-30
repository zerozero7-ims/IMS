package com.ims.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PagesController {
    @RequestMapping("/dashboard")
    public ModelAndView dashboard(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("spring","spring mvc");
        mv.setViewName("dashboard");
        return mv;
    }
    @RequestMapping("/typography")
    public ModelAndView typography(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("typography");
        return mv;
    }

    @RequestMapping("/desktop")
    public ModelAndView desktop(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/WEB-INF/pages/hello.jsp");
        return mv;
    }

    @RequestMapping("/elements")
    public ModelAndView elements(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("elements");
        return mv;
    }

    @RequestMapping("/buttons")
    public ModelAndView buttons(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("buttons");
        return mv;
    }

    @RequestMapping("/blank")
    public ModelAndView blank(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("blank");
        return mv;
    }

    @RequestMapping("/calendar")
    public ModelAndView calendar(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("calendar");
        return mv;
    }

    @RequestMapping("/dropzone")
    public ModelAndView dropzone(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("dropzone");
        return mv;
    }

    @RequestMapping("/error404")
    public ModelAndView error404(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("error-404");
        return mv;
    }

    @RequestMapping("/error500")
    public ModelAndView error500(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("error-500");
        return mv;
    }

    @RequestMapping("/faq")
    public ModelAndView faq(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("faq");
        return mv;
    }

    @RequestMapping("/formelements")
    public ModelAndView formelements(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("form-elements");
        return mv;
    }

    @RequestMapping("/formwizard")
    public ModelAndView formwizard(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("form-wizard");
        return mv;
    }

    @RequestMapping("/gallery")
    public ModelAndView gallery(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("gallery");
        return mv;
    }

    @RequestMapping("/grid")
    public ModelAndView grid(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("grid");
        return mv;
    }

    @RequestMapping("/inbox")
    public ModelAndView inbox(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("inbox");
        return mv;
    }

    @RequestMapping("/invoice")
    public ModelAndView invoice(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("invoice");
        return mv;
    }

    @RequestMapping("/jqgrid")
    public ModelAndView jqgrid(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("jqgrid");
        return mv;
    }

    @RequestMapping("/jqueryui")
    public ModelAndView jqueryui(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("jquery-ui");
        return mv;
    }


    @RequestMapping("/nestablelist")
    public ModelAndView nestablelist(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("nestable-list");
        return mv;
    }

    @RequestMapping("/pricing")
    public ModelAndView pricing(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pricing");
        return mv;
    }

    @RequestMapping("/profile")
    public ModelAndView profile(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("profile");
        return mv;
    }

    @RequestMapping("/tables")
    public ModelAndView tables(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("tables");
        return mv;
    }

    @RequestMapping("/timeline")
    public ModelAndView timeline(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("timeline");
        return mv;
    }

    @RequestMapping("/treeview")
    public ModelAndView treeview(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("treeview");
        return mv;
    }


    @RequestMapping("/widgets")
    public ModelAndView widgets(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("widgets");
        return mv;
    }

    @RequestMapping("/wysiwyg")
    public ModelAndView wysiwyg(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("wysiwyg");
        return mv;
    }


    @RequestMapping("/add")
    public ModelAndView add(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("add");
        return mv;
    }

    @RequestMapping("/look")
    public ModelAndView look(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("look");
        return mv;
    }

    @RequestMapping("/threemenu")
    public ModelAndView threemenu(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("threemenu");
        return mv;
    }


}
