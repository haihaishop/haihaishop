package com.shop.controller.goods.admin;

import com.alibaba.fastjson.JSONObject;
import com.shop.Utils.LoggingUtil;
import com.shop.model.domain.*;
import com.shop.model.service.*;
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

import java.io.File;
import java.util.ArrayList;
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
    @Autowired
    PromotionManagerInterface promotionService;
    @Autowired
    OrderManagerInterface orderManagerInterface;
    @Autowired
    UserManagerInterface userManagerInterface;
    @Autowired
    AddressManageInterface addressManageInterface;

    @RequestMapping("/{store_id}add_goods")
    public ModelAndView addGoods(@PathVariable("store_id")Long storeId,
                                 HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("store_id", storeId);
        Store store = shopService.getStoreByUsername(getUserName(request));
        modelAndView.addObject("store", store);
        List<Promotion> promotions = promotionService.getPromotions(storeId);
        modelAndView.addObject("promotions", promotions);
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
    public ModelAndView goodsEdit(@PathVariable("goods_id")Long goodsId,
                                  HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        Goods goods = goodsService.getGoodsById(goodsId);
        List<Cate> cates = cateService.getAllCates();
        List<Cate> goodsCates = goodsService.getAllCateByGoodsId(goodsId);
        modelAndView.addObject("cates", cates);
        modelAndView.addObject("goods", goods);
        modelAndView.addObject("goodsCates", goodsCates);
        Store store = shopService.getStoreByUsername(getUserName(request));
        List<Promotion> promotions = promotionService.getPromotions(store.getStore_id());
        modelAndView.addObject("promotions", promotions);
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

    @RequestMapping("goods_detail/{goods_id}")
    public ModelAndView goods_detail(@PathVariable("goods_id")Long goodsId,
                                     HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        Goods goods = goodsService.getGoodsById(goodsId);
        mav.addObject("goods",goods);
        Store store = shopService.getStoreByUsername(getUserName(request));
        mav.addObject("store", store);
        mav.setViewName("goods/goods_show/goods_detail_admin");
        return mav;
    }

    @RequestMapping("/{store_id}shop_order")
    public ModelAndView shop_order(@PathVariable("store_id")Long storeId){
        ModelAndView mav = new ModelAndView();
        List<OrderGoods> orderGoodsList = new ArrayList<OrderGoods>();
        List<Order_form> orderList = orderManagerInterface.getAllOrderBySolderId(shopService.getStoreByStoreId(storeId).getUser_id());
        for (Order_form order:orderList
             ) {
            OrderGoods orderGoods = new OrderGoods();
            orderGoods.setAddress(addressManageInterface.getAddressById(order.getAddress_id()));
            orderGoods.setBuyer(userManagerInterface.getUserById(order.getUser_id()));
            orderGoods.setSeller(userManagerInterface.getUserById(order.getSolder_id()));
            orderGoods.setGoods(goodsService.getGoodsById(order.getGoods_id()));
            orderGoods.setOrder(order);
            orderGoodsList.add(orderGoods);
        }
        mav.addObject("orderGoodsList",orderGoodsList);
        mav.setViewName("goods/goods_admin/shop_order");
        return mav;
    }

    @RequestMapping("/{store_id}send_goods/{order_id}")
    public ModelAndView send_goods(@PathVariable("store_id")Long storeId,@PathVariable("order_id")Long orderId){
        orderManagerInterface.changeShippingState(3,orderId);
        return new ModelAndView("redirect:/shop_admin/"+storeId+"shop_order");
    }
}
