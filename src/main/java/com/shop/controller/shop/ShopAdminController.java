package com.shop.controller.shop;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shop.Utils.LoggingUtil;
import com.shop.model.service.UserManagerInterface;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

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

    @RequestMapping("uploadImage")
    @ResponseBody
    public JSONObject uploadImage(HttpServletRequest request,
                                  @RequestParam("shop_image")MultipartFile image){
        JSONObject json = new JSONObject();
        try {
            LoggingUtil.log(image.getOriginalFilename());
            //文件保存路径: /static/images/upload/shop_image/
            String prefix = image.getOriginalFilename().substring(image.getOriginalFilename().lastIndexOf(".")+1);
            String imageName = getUserName(request) +"." + prefix;
            if (!image.isEmpty()) {
                // 设定保存文件的路径
                String dir = request.getSession().getServletContext().getRealPath("/WEB-INF/static/images/upload/shop_image");
                try {
                    FileUtils.writeByteArrayToFile(new File(dir, imageName), image.getBytes());
                    LoggingUtil.log(imageName);
                    json.put("success", "/images/upload/shop_image" + "\\" + imageName);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return json;
    }

    private String getUserName(HttpServletRequest request){
        SecurityContextImpl securityContext = (SecurityContextImpl) request
                .getSession()
                .getAttribute("SPRING_SECURITY_CONTEXT");
        return securityContext.getAuthentication().getName();
    }
}
