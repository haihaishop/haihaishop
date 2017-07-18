package com.shop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by 15764 on 2017-07-17.
 */
@Controller
@RequestMapping("/super_admin")
public class SuperAdmin {

    @RequestMapping({"/", ""})
    public ModelAndView homePage(){
        return new ModelAndView("admin/homepage");
    }

    @RequestMapping("/add_admin")
    public ModelAndView add_admin(){
        return new ModelAndView("admin/add_admin");
    }
}
