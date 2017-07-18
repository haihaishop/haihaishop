package com.shop.controller.admin;

import com.shop.Utils.LoggingUtil;
import com.shop.model.domain.User;
import com.shop.model.service.Manager.RoleManager;
import com.shop.model.service.RoleManagerInterface;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by 15764 on 2017-07-17.
 */
@Controller
@RequestMapping("/super_admin")
public class SuperAdmin {
    @Value("#{roleManager}")
    private RoleManagerInterface roleManagerInterface;

    @RequestMapping({"/", ""})
    public ModelAndView homePage(){
        return new ModelAndView("admin/homepage");
    }

    @RequestMapping("/add_admin")
    public ModelAndView add_admin(){
        LoggingUtil.log(SuperAdmin.class, roleManagerInterface.getRoleIdFromName("admin"));
        return new ModelAndView("admin/add_admin");
    }

    @RequestMapping("/add_user_post")
    public ModelAndView add_user_post(User user){
        LoggingUtil.log(SuperAdmin.class, "666666");
        return new ModelAndView("redirect:/super_admin/add_admin");
    }
}
