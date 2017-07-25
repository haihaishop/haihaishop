package com.shop.controller.orderController;

import com.shop.model.domain.Order_form;
import com.shop.model.service.OrderManagerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by 18240 on 2017/7/25.
 */
@Controller
public class OrderController {
    @Autowired
    private OrderManagerInterface orderManagerInterface;

    @RequestMapping("place_order.do")
    public ModelAndView pay_order(@RequestParam(value = "orderId[]", required = false)Long[] orderid){
        ModelAndView mav = new ModelAndView();
        float total = 0;
        for(int i = 0; i < orderid.length; i++){
            if(orderid[i] != null){
                orderManagerInterface.changePlaceState(true,orderid[i]);
                Order_form order =orderManagerInterface.getOrderById(orderid[i]);
                total += order.getBuy_number() * order.getUnit_price();
            }
        }
        mav.addObject("total", total);
        mav.setViewName("order/pay_order");
        return mav;
    }
}
