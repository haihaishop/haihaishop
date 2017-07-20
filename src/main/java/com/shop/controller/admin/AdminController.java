package com.shop.controller.admin;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("admin")
public class AdminController {
    @RequestMapping({"/", ""})
    public ModelAndView homePage(){
        return new ModelAndView("admin/homepage");
    }

    @RequestMapping("/cate_manage")
    public String cateManage(){
        return "admin/cate_manage";
    }

}
