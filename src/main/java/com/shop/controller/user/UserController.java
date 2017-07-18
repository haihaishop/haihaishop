package com.shop.controller.user;

import com.shop.Utils.LoggingUtil;
import com.shop.Utils.SHAUtil;
import com.shop.model.domain.User;
import com.shop.model.service.RoleManagerInterface;
import com.shop.model.service.UserManagerInterface;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by 18240 on 2017/7/17.
 */
@Controller
public class UserController {
    @Value("#{roleManager}")
    private RoleManagerInterface roleManagerInterface;
    @Value("#{userManager}")
    private UserManagerInterface userManagerInterface;

    @RequestMapping("register.do")
    public ModelAndView register() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("user/register");
        return mav;
    }

    @RequestMapping("login.do")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("user/login");
        return mav;
    }

    @RequestMapping("user_login.do")
    public ModelAndView user_login(User user) {
        ModelAndView mav = new ModelAndView();
        if(userManagerInterface.hasUser(user.getLogin_name())){

        }
        mav.setViewName("/index");
        return mav;
    }

    @RequestMapping("user_register")
    public ModelAndView user_register(User user, @RequestParam("role") String roleName) {
        ModelAndView mav = new ModelAndView();
        if (userManagerInterface.hasUser(user.getLogin_name())) {
            mav.addObject("hasUser","用户名已存在！");
        }
        else{
            Long tempRoleId = roleManagerInterface.getRoleIdFromName("roleName");
            user.setRole_id(tempRoleId);
            user.setPassword(SHAUtil.SHA256(user.getPassword()));
            userManagerInterface.addUser(user);
            mav.addObject("registerSuccessful","注册成功！");
        }
        mav.setViewName("user/login");
        return mav;
    }
}
