package com.shop.controller.shopAdmin;


import com.alibaba.fastjson.JSONObject;
import com.shop.model.domain.Store;
import com.shop.model.service.ShopManageInterface;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
@RequestMapping("/shop_admin")
public class ShopAdminController {
    @Autowired
    private UserManagerInterface userService;
    @Autowired
    private ShopManageInterface shopService;

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
            //获取文件后缀名
            String prefix = image.getOriginalFilename().substring(image.getOriginalFilename().lastIndexOf(".")+1);
            //文件名是用户名，可以保证不会重复
            String imageName = getUserName(request) +"." + prefix;
            if (!image.isEmpty()) {
                //文件保存路径: /static/images/upload/shop_image/
                String dir = request.getSession().getServletContext().getRealPath("/WEB-INF/static/images/upload/shop_image");
                FileUtils.writeByteArrayToFile(new File(dir, imageName), image.getBytes());
                json.put("path", "/images/upload/shop_image" + "\\" + imageName);//保存地址
                json.put("msg","保存成功！");
            }
        }
        catch (Exception e){
            e.printStackTrace();
            json.put("msg","保存失败，发生了未知错误..");
        }
        return json;
    }

    @RequestMapping("/create_shop_post")
    public String createShopPost(Store store,
                                 HttpServletRequest request,
                                 RedirectAttributes model){
        if (shopService.hasShop(store.getStore_name())){
            model.addFlashAttribute("store", store);
            model.addFlashAttribute("warning", "店铺已存在，请修改店铺名");
            return "redirect:/shop_admin/create";
        }
        else {
            shopService.addShop(store, getUserName(request));
            model.addFlashAttribute("success", "创建成功");
            return "redirect:/shop_admin/detail";
        }
    }

    @RequestMapping("/detail")
    public ModelAndView shopDetail(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("shop_admin/shop_detail");
        Store store = shopService.getStoreByUsername(getUserName(request));
        modelAndView.addObject("store", store);
        return modelAndView;
    }



    private String getUserName(HttpServletRequest request){
        SecurityContextImpl securityContext = (SecurityContextImpl) request
                .getSession()
                .getAttribute("SPRING_SECURITY_CONTEXT");
        return securityContext.getAuthentication().getName();
    }
}
