package com.shop.controller.shop;

import com.shop.model.service.UserManagerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/shop_admin")
public class ShopAdminController {
    @Autowired
    private UserManagerInterface userService;

    @RequestMapping("/create")
    public ModelAndView createShop(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("shop_admin/shop_create");
        return modelAndView;
    }

    private String getUserName(HttpServletRequest request){
        SecurityContextImpl securityContext = (SecurityContextImpl) request
                .getSession()
                .getAttribute("SPRING_SECURITY_CONTEXT");
        return securityContext.getAuthentication().getName();
    }
}
