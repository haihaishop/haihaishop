package com.shop.controller.admin;

import com.shop.Utils.LoggingUtil;
import com.shop.Utils.SHAUtil;
import com.shop.model.domain.User;
import com.shop.model.service.Manager.RoleManager;
import com.shop.model.service.RoleManagerInterface;
import com.shop.model.service.UserManagerInterface;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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
    public String add_user_post(User user, RedirectAttributes model){
        if (userManager.hasUser(user.getLogin_name())){
            model.addFlashAttribute("hasUser", "用户已存在！");
        }
        else {
            user.setRole_id(roleManagerInterface.getRoleIdFromName("admin"));
            user.setPassword(SHAUtil.SHA256(user.getPassword()));
            userManager.addUser(user);
            model.addFlashAttribute("regSuccess", "恭喜，添加管理员成功！");
        }
        return "redirect:/super_admin/add_admin";
    }

    @RequestMapping("/get_admins")
    public ModelAndView get_admins(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/get_admins");
        List<User> admins;
        admins = userManager.getAllAdmins();
        modelAndView.addObject("admins",admins);
        return modelAndView;
    }

    @RequestMapping("/delete_admin")
    public ModelAndView delete_admin(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/delete_admin");
        List<User> admins;
        admins = userManager.getAllAdmins();
        modelAndView.addObject("admins",admins);
        return modelAndView;
    }

    @RequestMapping("/delete_admin/{admin_name}")
    public String delete_admin(@PathVariable("admin_name")String admin_name, RedirectAttributes model){
        if (userManager.hasUser(admin_name)){
            userManager.deleteUserByLoginName(admin_name);
            model.addFlashAttribute("success", "删除成功！");
            return "redirect:/super_admin/delete_admin";
        }else {
            model.addFlashAttribute("warning", "用户不存在！");
            return "redirect:/super_admin/delete_admin";
        }
    }

}
