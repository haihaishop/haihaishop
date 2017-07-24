package com.shop.controller.goodsAdmin;

import com.alibaba.fastjson.JSONObject;
import com.shop.Utils.LoggingUtil;
import com.shop.model.domain.Cate;
import com.shop.model.domain.Goods;
import com.shop.model.domain.Store;
import com.shop.model.domain.User;
import com.shop.model.service.CateManagerInterface;
import com.shop.model.service.GoodsManageInterface;
import com.shop.model.service.ShopManageInterface;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

/**
 *货物增删改查
 */
@Controller
@RequestMapping("/shop_admin")
public class GoodsAdminController {
    @Autowired
    ShopManageInterface shopService;
    @Autowired
    CateManagerInterface cateService;
    @Autowired
    GoodsManageInterface goodsService;

    @RequestMapping("/{store_id}add_goods")
    public ModelAndView addGoods(@PathVariable("store_id")Long storeId,
                                 HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("store_id", storeId);
        Store store = shopService.getStoreByUsername(getUserName(request));
        modelAndView.addObject("store", store);
        List<Cate> cates = cateService.getAllCates();
        modelAndView.addObject("cates", cates);
        modelAndView.setViewName("goods/goods_admin/add_goods");
        return modelAndView;
    }

    @RequestMapping("goodsImage")
    @ResponseBody
    public JSONObject goodsImage(@RequestParam("goods_image")MultipartFile image,
                                 HttpServletRequest request){
        JSONObject json = new JSONObject();
        try {
            //获取文件后缀名
            String prefix = image.getOriginalFilename().substring(image.getOriginalFilename().lastIndexOf(".")+1);
            //文件名是图片名+用户名+一个数字，并且保证不会重复
            String imageName = getUserName(request) +"." + prefix;
            if (!image.isEmpty()) {
                //文件保存路径: /static/images/upload/shop_image/
                Integer i = 0;
                String dir = request.getSession().getServletContext().getRealPath("/WEB-INF/static/images/upload/goods/"+getUserName(request)+"/");
                LoggingUtil.log(dir);
                while(true){
                    imageName = getUserName(request) + i.toString() +"." + prefix;
                    LoggingUtil.log(image);
                    File file = new File(dir, imageName);
                    if (file.exists()){
                        i++;
                    }
                    else {
                        FileUtils.writeByteArrayToFile(new File(dir, imageName), image.getBytes());
                        json.put("path", "/images/upload/goods/"+getUserName(request)+"/" + imageName);//保存地址
                        json.put("msg","保存成功！");
                        break;
                    }
                }

            }
        }
        catch (Exception e){
            e.printStackTrace();
            json.put("msg","保存失败，发生了未知错误..");
        }
        return json;
    }

    @RequestMapping("/{store_id}add_goods_post")
    public String addGoodsPost(Goods goods,
                               RedirectAttributes model,
                               @PathVariable("store_id")Long storeId,
                               @RequestParam(value = "cate[]", required = false)Long[] allCateId){
        goods.setStore_id(storeId);
        goods.setDate(new Date());
        goodsService.addGoods(goods, allCateId);
        model.addFlashAttribute("success", "创建成功");
        return "redirect:/shop_admin/shop";
    }

    @RequestMapping("/goods_edit/{goods_id}")
    public ModelAndView goodsEdit(@PathVariable("goods_id")Long goodsId){
        ModelAndView modelAndView = new ModelAndView();
        Goods goods = goodsService.getGoodsById(goodsId);
        List<Cate> cates = cateService.getAllCates();
        List<Cate> goodsCates = goodsService.getAllCateByGoodsId(goodsId);
        for (Cate cate:goodsCates
             ) {
            LoggingUtil.log(cate.getCate_name());
            LoggingUtil.log(cate.getCate_id());
        }
        modelAndView.addObject("cates", cates);
        modelAndView.addObject("goods", goods);
        modelAndView.addObject("goodsCates", goodsCates);
        modelAndView.setViewName("goods/goods_admin/edit_goods");
        return modelAndView;
    }

    @RequestMapping("/edit_goods_post")
    public String editGoodsPost(Goods goods,
                                RedirectAttributes model,
                                @RequestParam(value = "cate[]", required = false)Long[] allCateId){
        goodsService.changeGoods(goods, allCateId);
        model.addFlashAttribute("success", "修改成功");
        return "redirect:/shop_admin/shop";
    }

    @RequestMapping("/goods_delete/{goods_id}")
    public String  goodsDelete(@PathVariable("goods_id")Long goodsId,
                                    RedirectAttributes model){
        goodsService.deleteGoods(goodsId);
        model.addFlashAttribute("success","删除成功");
        return "redirect:/shop_admin/shop";
    }
}
