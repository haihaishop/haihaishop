package com.shop.controller.user;

import com.shop.Utils.LoggingUtil;
import com.shop.model.domain.User;
import com.shop.model.service.RoleManagerInterface;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by 18240 on 2017/7/17.
 */
@Controller
public class UserController {
    @Value("#{roleManager}")
    private RoleManagerInterface roleManagerInterface;
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
        mav.setViewName("/index");
        return mav;
    }

    @RequestMapping("user_register")
    public ModelAndView user_register(User user, @RequestParam("role")String roleName) {
        user.setRole_id(roleManagerInterface.getRoleIdFromName("roleName"));

        ModelAndView mav = new ModelAndView();
        mav.setViewName("user/login");
        return mav;
    }
}
