package com.shop.controller.shopAdmin;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.shop.Utils.UserUtil;
import com.shop.model.domain.Goods;
import com.shop.model.domain.Store;
import com.shop.model.domain.User;
import com.shop.model.service.GoodsManageInterface;
import com.shop.model.service.ShopManageInterface;
import com.shop.model.service.UserManagerInterface;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Date;
import java.util.List;

import static com.shop.Utils.UserUtil.getUserName;

@Controller
@RequestMapping("/shop_admin")
public class ShopAdminController {
    @Autowired
    private UserManagerInterface userService;
    @Autowired
    private ShopManageInterface shopService;
    @Autowired
    private GoodsManageInterface goodsService;

    @RequestMapping("/create")
    public ModelAndView createShop(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("shop/shop_admin/shop_create");
        return modelAndView;
    }

    @RequestMapping("shopImage")
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
            store.setCreate_date(new Date());
            shopService.addShop(store, getUserName(request));
            model.addFlashAttribute("success", "创建成功");
            User user = userService.getUserByLoginName(getUserName(request));
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            return "redirect:/shop_admin/detail";
        }
    }

    @RequestMapping("/detail")
    public ModelAndView shopDetail(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("shop/shop_admin/shop_detail");
        Store store = shopService.getStoreByUsername(getUserName(request));
        modelAndView.addObject("store", store);
        return modelAndView;
    }

    @RequestMapping("/shop")
    public ModelAndView shop(HttpServletRequest request,
                             @RequestParam(value = "page", required = false, defaultValue = "1")int page,
                             @RequestParam(value = "rows", required = false, defaultValue = "18")int rows){
        ModelAndView modelAndView = new ModelAndView();
        Store store = shopService.getStoreByUsername(getUserName(request));
        List<Goods> goodsList = goodsService.getGoodsByStoreId(store.getStore_id(), page, rows);
        modelAndView.addObject("goodsList", goodsList);
        PageInfo<Goods> pageInfo =new  PageInfo<Goods>(goodsList);
        modelAndView.addObject("pageInfo", pageInfo);
        modelAndView.addObject("store", store);
        modelAndView.setViewName("shop/shop_admin/shop_home");
        return modelAndView;
    }

    @RequestMapping("/{store_id}edit_goods")
    public ModelAndView shop(HttpServletRequest request,
                             @PathVariable("store_id")Long storeId,
                             @RequestParam(value = "page", required = false, defaultValue = "1")int page,
                             @RequestParam(value = "rows", required = false, defaultValue = "18")int rows){
        ModelAndView modelAndView = new ModelAndView();
        Store store = shopService.getStoreByUsername(getUserName(request));
        List<Goods> goodsList = goodsService.getGoodsByStoreId(storeId, page, rows);
        modelAndView.addObject("goodsList", goodsList);
        PageInfo<Goods> pageInfo =new  PageInfo<Goods>(goodsList);
        modelAndView.addObject("pageInfo", pageInfo);
        modelAndView.addObject("store", store);
        modelAndView.setViewName("shop/shop_admin/shop_edit_goods");
        return modelAndView;
    }

    @RequestMapping("/change_shop_post")
    public String changeShopPost(Store store, RedirectAttributes model){
        if (shopService.hasShop(store.getStore_name())){
            model.addFlashAttribute("warning", "店铺已存在，请修改店铺名");
        }
        else {
            shopService.changeStore(store);
            model.addFlashAttribute("success", "修改成功！");
        }
        return "redirect:/shop_admin/detail";
    }

}
