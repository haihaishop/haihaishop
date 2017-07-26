package com.shop.controller.order;

import com.shop.Utils.LoggingUtil;
import com.shop.model.domain.*;
import com.shop.model.service.*;
import org.apache.xpath.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 18240 on 2017/7/25.
 */
@Controller
public class OrderController {
    @Autowired
    private OrderManagerInterface orderManagerInterface;
    @Autowired
    private AddressManageInterface addressManageInterface;
    @Autowired
    private UserManagerInterface userManagerInterface;
    @Autowired
    private UserAddressManageInterface userAddressManageInterface;
    @Autowired
    private GoodsManageInterface goodsManageInterface;

    @RequestMapping("place_order.do")
    public ModelAndView pay_order(@RequestParam(value = "orderId[]", required = false) Long[] orderid) {
        ModelAndView mav = new ModelAndView();
        float total = 0;
        for (int i = 0; i < orderid.length; i++) {
            if (orderid[i] != null) {
                orderManagerInterface.changeShippingState(1, orderid[i]);
                Order_form order = orderManagerInterface.getOrderById(orderid[i]);
                total += order.getBuy_number() * order.getUnit_price();
            }
        }
        mav.addObject("total", total);
        mav.setViewName("order/pay_order");
        return mav;
    }

    @RequestMapping("pay_order.do")
    public ModelAndView pay_order(Address address, HttpServletRequest request) {
        SecurityContextImpl securityContext = (SecurityContextImpl) request
                .getSession()
                .getAttribute("SPRING_SECURITY_CONTEXT");
        String username = securityContext.getAuthentication().getName();
        ModelAndView mav = new ModelAndView();
        User user = userManagerInterface.getUserByLoginName(username);
        addressManageInterface.addAddress(address);
        userAddressManageInterface.addUserAddress(user.getUser_id(), address.getAddress_id());
        List<Order_form> orderListState1 = orderManagerInterface.getAllStateOrderByUserId(1, user.getUser_id());
        for (Order_form orderState1: orderListState1
             ) {
            orderManagerInterface.changeShippingState(2, orderState1.getOrder_form_id());
            if(orderState1.getBuy_number() > goodsManageInterface.getGoodsById(orderState1.getGoods_id()).getCount()){
                mav.setViewName("order/pay_order");
                mav.addObject("error","商品数量不足");
                return mav;
            }else {
                Long num = goodsManageInterface.getGoodsById(orderState1.getGoods_id()).getCount() - orderState1.getBuy_number();
                Goods goods = goodsManageInterface.getGoodsById(orderState1.getGoods_id());
                goods.setCount(num);
                goods.setSold_number(goods.getSold_number() + orderState1.getBuy_number());
                goodsManageInterface.changeGoods(goods);
            }
        }
        mav.setViewName("redirect:/order_information.do");
        return mav;
    }

    @RequestMapping("order_information.do")
    public ModelAndView order_information(HttpServletRequest request){
        SecurityContextImpl securityContext = (SecurityContextImpl) request
                .getSession()
                .getAttribute("SPRING_SECURITY_CONTEXT");
        String username = securityContext.getAuthentication().getName();
        ModelAndView mav = new ModelAndView();
        List<Order_form> orderLists = orderManagerInterface.getAllOrderByUserId(userManagerInterface.getUserByLoginName(username).getUser_id());
        List<OrderGoods> orderGoods = new ArrayList<OrderGoods>();
        for (int i = 0; i < orderLists.size(); i++) {
            if (orderLists.get(i)!=null && orderLists.get(i).getPay_state()) {
                orderManagerInterface.getOrderById(orderLists.get(i).getOrder_form_id());
                OrderGoods tempOrderGoods = new OrderGoods();
                tempOrderGoods.setAddress(addressManageInterface.getAddressById(orderLists.get(i).getAddress_id()));
                tempOrderGoods.setGoods(goodsManageInterface.getGoodsById(orderLists.get(i).getGoods_id()));
                tempOrderGoods.setOrder(orderLists.get(i));
                orderGoods.add(tempOrderGoods);
            }
        }
        mav.addObject("orderGoodsList", orderGoods);
        mav.setViewName("order/order_information");
        return mav;
    }
}
