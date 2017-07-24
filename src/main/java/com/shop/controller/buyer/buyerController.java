package com.shop.controller.buyer;

import com.shop.model.domain.Cate;
import com.shop.model.domain.Goods;
import com.shop.model.domain.Order_form;
import com.shop.model.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.management.MalformedObjectNameException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by 18240 on 2017/7/20.
 */
@Controller
public class buyerController {

    @Autowired
    private CateManagerInterface cateManagerInterface;
    @Autowired
    private GoodsManageInterface goodsManagerInterface;
    @Autowired
    private OrderManagerInterface orderManagerInterface;
    @Autowired
    private UserManagerInterface userManagerInterface;
    @Autowired
    private ShopManageInterface shopManageInterface;

    @RequestMapping("buyer_home_page.do/{cate_id}")
    public ModelAndView cateClick(@PathVariable("cate_id")Long cateId){
        ModelAndView mav = new ModelAndView();
        List<Cate> cateList = cateManagerInterface.getAllCates();
        List<Goods> goodsList = goodsManagerInterface.getAllGoodsByCateId(cateId);
        mav.addObject("cateList", cateList);
        mav.addObject("goodsList",goodsList);
        mav.setViewName("buyer/buyer_home_page");
        return mav;
    }

    @RequestMapping("buyer_home_page.do")
    public ModelAndView buyer_home_page(){
        ModelAndView mav = new ModelAndView();
        List<Cate> cateList = cateManagerInterface.getAllCates();
        mav.addObject("cateList", cateList);
        mav.setViewName("buyer/buyer_home_page");
        return mav;
    }

    @RequestMapping("goods_detail.do/{goods_id}")
    public ModelAndView goods_detail(@PathVariable("goods_id")Long goodsId){
        ModelAndView mav = new ModelAndView();
        Goods goods = goodsManagerInterface.getGoodsById(goodsId);
        mav.addObject("goods",goods);
        mav.setViewName("goods/goods_show/goods_detail");
        return mav;
    }

    @RequestMapping("add_shopping_cart.do/{buy_count}/{goods_id}")
    public void add_shopping_cart(
            HttpServletRequest request,
            @PathVariable("buy_count")Long buyCount,
            @PathVariable("goods_id")Long goodsId){
        SecurityContextImpl securityContext = (SecurityContextImpl) request
                .getSession()
                .getAttribute("SPRING_SECURITY_CONTEXT");
        String username = securityContext.getAuthentication().getName();
        Order_form orderForm = new Order_form();
        orderForm.setBuy_number(buyCount);
        orderForm.setDate(new Date());
        orderForm.setGoods_id(goodsId);
        orderForm.setUser_id(userManagerInterface.getUserByLoginName(username).getUser_id());
        orderForm.setSolder_id(shopManageInterface.getStoreByStoreId(goodsManagerInterface.getGoodsById(goodsId).getStore_id()).getUser_id());
        orderManagerInterface.addShoppingCart(orderForm);
    }
}
