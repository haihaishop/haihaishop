package com.shop.controller.admin;

import com.shop.Utils.LoggingUtil;
import com.shop.Utils.SHAUtil;
import com.shop.model.domain.User;
import com.shop.model.service.Manager.RoleManager;
import com.shop.model.service.RoleManagerInterface;
import com.shop.model.service.UserManagerInterface;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by 15764 on 2017-07-17.
 */
@Controller
@RequestMapping("/super_admin")
public class SuperAdmin {
    @Value("#{roleManager}")
    private RoleManagerInterface roleManagerInterface;
    @Value("#{userManager}")
    private UserManagerInterface userManager;

    @RequestMapping({"/", ""})
    public ModelAndView homePage(){
        return new ModelAndView("admin/homepage");
    }

    @RequestMapping("/add_admin")
    public ModelAndView add_admin(){
        LoggingUtil.log(roleManagerInterface.getRoleIdFromName("seller"));
        return new ModelAndView("admin/add_admin");
    }

    @RequestMapping("/add_user_post")
    public ModelAndView add_user_post(User user){
        ModelAndView modelAndView = new ModelAndView();
        if (userManager.hasUser(user.getLogin_name())){
            modelAndView.addObject("hasUser", "用户已存在！");
        }
        else {
            user.setRole_id(roleManagerInterface.getRoleIdFromName("admin"));
            user.setPassword(SHAUtil.SHA256(user.getPassword()));
            userManager.addUser(user);
            modelAndView.addObject("regSuccess", "恭喜，添加管理员成功！");
        }
        modelAndView.setViewName("admin/add_admin");
        return modelAndView;
    }
}
