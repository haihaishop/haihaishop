package com.shop.controller.admin;

import com.shop.Utils.BCryptUtil;
import com.shop.model.domain.User;
import com.shop.model.service.RoleManagerInterface;
import com.shop.model.service.UserManagerInterface;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

/**
 * Created by 15764 on 2017-07-17.
 */
@Controller
@RequestMapping("/super_admin")
public class SuperAdminController {
    @Value("#{roleService}")
    private RoleManagerInterface roleManagerInterface;
    @Value("#{userService}")
    private UserManagerInterface userManager;

    @RequestMapping({"/", ""})
    public ModelAndView homePage(){
        return new ModelAndView("super_admin/homepage");
    }

    @RequestMapping("/add_admin")
    public ModelAndView add_admin(){
        return new ModelAndView("super_admin/add_admin");
    }

    @RequestMapping("/add_user_post")
    public String add_user_post(User user, RedirectAttributes model){
        if (userManager.hasUser(user.getUsername())){
            model.addFlashAttribute("hasUser", "用户已存在！");
        }
        else {
            user.setRole_id(roleManagerInterface.getRoleIdFromName("ROLE_ADMIN"));
            user.setPassword(BCryptUtil.encode(user.getPassword()));
            user.setCreate_date(new Date());
            userManager.addUser(user);
            model.addFlashAttribute("regSuccess", "添加管理员成功！");
        }
        return "redirect:/super_admin/add_admin";
    }

    @RequestMapping("/get_admins")
    public ModelAndView get_admins(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("super_admin/get_admins");
        List<User> admins;
        admins = userManager.getAllAdmins();
        modelAndView.addObject("admins",admins);
        return modelAndView;
    }

    @RequestMapping("/delete_admin")
    public ModelAndView delete_admin(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("super_admin/delete_admin");
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
        }else {
            model.addFlashAttribute("warning", "用户不存在！");
        }
        return "redirect:/super_admin/delete_admin";
    }

    @RequestMapping("/change_password_post")
    public String changePasswordPost(@RequestParam("oldPassword")String oldPassword,
                                       @RequestParam("password")String newPassword,
                                     RedirectAttributes model){
        ModelAndView modelAndView = new ModelAndView();
        if (userManager.authUser("admin", oldPassword)){
            userManager.changePasswordByUsername("admin", newPassword);
            model.addFlashAttribute("success", "修改成功！");
        }
        else {
            model.addFlashAttribute("warning", "原密码不正确！");
        }
        return "redirect:/super_admin/change_password";
    }

    @RequestMapping("/change_password")
    public String changePassword(){
        return "super_admin/change_password";
    }

}
