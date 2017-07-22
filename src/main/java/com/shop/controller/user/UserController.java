package com.shop.controller.user;

import com.shop.Utils.BCryptUtil;
import com.shop.Utils.LoggingUtil;
import com.shop.model.domain.User;
import com.shop.model.service.RoleManagerInterface;
import com.shop.model.service.UserManagerInterface;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Date;

/**
 * Created by 18240 on 2017/7/17.
 */
@Controller
public class UserController {
    @Value("#{roleService}")
    private RoleManagerInterface roleManagerInterface;
    @Value("#{userService}")
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

    @RequestMapping("information.do")
    public ModelAndView information(HttpServletRequest request) {
        SecurityContextImpl securityContext = (SecurityContextImpl) request
                .getSession()
                .getAttribute("SPRING_SECURITY_CONTEXT");
        String username = securityContext.getAuthentication().getName();
        User user = userManagerInterface.getUserByLoginName(username);
        ModelAndView mav = new ModelAndView();
        mav.addObject("user", user);
        mav.setViewName("user/information");
        return mav;
    }

    @RequestMapping("/user_register.do")
    public ModelAndView user_register(User user, RedirectAttributes model) {
        if (userManagerInterface.hasUser(user.getUsername())) {
            LoggingUtil.log("hasUser");
            model.addFlashAttribute("hasUser", "用户已存在！");
            return new ModelAndView("user/register");
        } else {
            user.setRole_id((long) 5);
            user.setCreate_date(new Date());
            user.setPassword(BCryptUtil.encode(user.getPassword()));
            userManagerInterface.addUser(user);
            model.addFlashAttribute("registerSuccess", "注册成功");
            return new ModelAndView("redirect:/login.do");
        }
    }

    @RequestMapping("/loginSuccess.do")
    public ModelAndView loginSuccess(HttpServletRequest request) {
        SecurityContextImpl securityContext = (SecurityContextImpl) request
                .getSession()
                .getAttribute("SPRING_SECURITY_CONTEXT");
        String username = securityContext.getAuthentication().getName();
        int roleId = userManagerInterface.getRoleIdByUsername(username);
        String roleName = roleManagerInterface.getNameFromRoleId(roleId);
        if (roleName.equals("ROLE_SUPER")) {
            return new ModelAndView("redirect:/super_admin");
        } else if (roleName.equals("ROLE_ADMIN")) {
            return new ModelAndView("redirect:/admin");
        } else if (roleName.equals("ROLE_USER")) {
            return new ModelAndView("redirect:/");
        } else {
            return new ModelAndView("redirect:/error");
        }

    }

    @RequestMapping("loginFailed.do")
    public ModelAndView loginFailed() {
        LoggingUtil.log("fff");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/login.do");
        return mav;
    }
}
