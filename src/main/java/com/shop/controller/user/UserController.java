package com.shop.controller.user;

import com.alibaba.fastjson.JSONObject;
import com.shop.Utils.BCryptUtil;
import com.shop.Utils.LoggingUtil;
import com.shop.model.domain.User;
import com.shop.model.service.RoleManagerInterface;
import com.shop.model.service.UserManagerInterface;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;
import java.util.Date;

import static com.shop.Utils.UserUtil.getUserName;

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
            model.addFlashAttribute("hasUser", "用户已存在！");
            return new ModelAndView("redirect:/register.do");
        } else {
            long roleId = roleManagerInterface.getRoleIdFromName("ROLE_USER");
            user.setRole_id(roleId);
            user.setCreate_date(new Date());
            user.setPassword(BCryptUtil.encode(user.getPassword()));
            userManagerInterface.addUser(user);
            model.addFlashAttribute("registerSuccess", "注册成功");
            return new ModelAndView("redirect:/login.do");
        }
    }

    @RequestMapping("/loginSuccess.do")
    public String loginSuccess(HttpServletRequest request) {
        SecurityContextImpl securityContext = (SecurityContextImpl) request
                .getSession()
                .getAttribute("SPRING_SECURITY_CONTEXT");
        HttpSession session = request.getSession();
        String username = securityContext.getAuthentication().getName();
        int roleId = userManagerInterface.getRoleIdByUsername(username);
        String roleName = roleManagerInterface.getNameFromRoleId(roleId);
        User user = userManagerInterface.getUserByLoginName(username);
        session.setAttribute("user", user);
        if (roleName.equals("ROLE_SUPER")) {
            return "redirect:/super_admin";
        } else if (roleName.equals("ROLE_ADMIN")) {
            return "redirect:/admin";
        } else if (roleName.equals("ROLE_USER")) {
            return "redirect:/";
        } else {
            return "redirect:/error";
        }
    }

    @RequestMapping("change_information.do")
    public ModelAndView change_information(User user){
        LoggingUtil.log(user.getSex());
        userManagerInterface.changeInformationByUsername(user);
        return new ModelAndView("redirect:/information.do");
    }

    @RequestMapping("authentication.do")
    public ModelAndView authentication(User user,
                                       @RequestParam("user_name")String user_name,
                                       @RequestParam("image")String image){
        user.setUsername(user_name);
        LoggingUtil.log(image);
        userManagerInterface.authentication(user);
        return new ModelAndView("redirect:/information.do");
    }

    @RequestMapping("loginFailed.do")
    public ModelAndView loginFailed(RedirectAttributes model) {
        LoggingUtil.log("fff");
        ModelAndView mav = new ModelAndView();
        model.addFlashAttribute("loginFailed","用户名或密码错误");
        mav.setViewName("redirect:/login.do");
        return mav;
    }

    @RequestMapping("user_image")
    @ResponseBody
    public JSONObject user_image(HttpServletRequest request,
                                 @RequestParam("user_image")MultipartFile image){
        JSONObject json = new JSONObject();
        try {
            //获取文件后缀名
            String prefix = image.getOriginalFilename().substring(image.getOriginalFilename().lastIndexOf(".")+1);
            //文件名是用户名，可以保证不会重复
            String imageName = getUserName(request) +"." + prefix;
            if (!image.isEmpty()) {
                //文件保存路径: /static/images/upload/user_image/
                String dir = request.getSession().getServletContext().getRealPath("/WEB-INF/static/images/upload/user_image");
                FileUtils.writeByteArrayToFile(new File(dir, imageName), image.getBytes());
                json.put("path", "/images/upload/user_image" + "\\" + imageName);//保存地址
                json.put("msg","保存成功！");
            }
        }
        catch (Exception e){
            e.printStackTrace();
            json.put("msg","保存失败，发生了未知错误..");
        }
        return json;
    }
}
