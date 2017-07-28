package com.shop.controller.buyer;

import com.github.pagehelper.PageInfo;
import com.shop.Utils.LoggingUtil;
import com.shop.Utils.UserUtil;
import com.shop.model.domain.*;
import com.shop.model.service.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.management.MalformedObjectNameException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by 18240 on 2017/7/20.
 */
@Controller
public class BuyerController {

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
    @Autowired
    private CommentManageInterface commentManageInterface;
    @Autowired
    private PromotionManagerInterface promotionService;

    @RequestMapping("buyer_home_page.do/{cate_id}")
    public ModelAndView cateClick(@PathVariable("cate_id") Long cateId,
                                  @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                  @RequestParam(value = "rows", required = false, defaultValue = "20") int rows) {
        ModelAndView mav = new ModelAndView();
        List<Cate> cateList = cateManagerInterface.getAllCates();
        List<Goods> goodsList = goodsManagerInterface.getAllGoodsByCateId(cateId, page, rows);
        mav.addObject("cateList", cateList);
        PageInfo<Goods> pageInfo = new PageInfo<Goods>(goodsList);
        mav.addObject("goodsList", goodsList);
        mav.addObject("pageInfo", pageInfo);
        mav.addObject("cate_id", cateId);
        mav.setViewName("goods/goods_show/buyer_home_page");
        return mav;
    }

    @RequestMapping("buyer_home_page.do")
    public ModelAndView buyer_home_page(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "rows", required = false, defaultValue = "20") int rows
    ) {
        ModelAndView mav = new ModelAndView();
        List<Cate> cateList = cateManagerInterface.getAllCates();
        List<Goods> goodsList = goodsManagerInterface.getAllGoods(page, rows);
        mav.addObject("cateList", cateList);
        PageInfo<Goods> pageInfo = new PageInfo<Goods>(goodsList);
        mav.addObject("goodsList", goodsList);
        mav.addObject("pageInfo", pageInfo);
        mav.setViewName("goods/goods_show/buyer_home_page");
        return mav;
    }

    @RequestMapping("goods_detail.do/{goods_id}")
    public ModelAndView goods_detail(@PathVariable("goods_id")Long goodsId,
                                     HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        Goods goods = goodsManagerInterface.getGoodsById(goodsId);
        List<Comment_table> commentList = commentManageInterface.getCommentByGoodsId(goodsId);
        mav.addObject("commentList",commentList);
        User user = userManagerInterface.getUserByLoginName(UserUtil.getUserName(request));
        mav.addObject("user",user);
        mav.addObject("goods",goods);
        Store store = shopManageInterface.getStoreByStoreId(goods.getStore_id());
        mav.addObject("store", store);
        User solder = userManagerInterface.getUserById(store.getUser_id());
        mav.addObject("solder", solder);
        if(goods.getPromotion_id() != null){
        Promotion promotion = promotionService.getPromotionById(goods.getPromotion_id());
        mav.addObject("promotion", promotion);
        }
        goodsManagerInterface.increaseViewsTime(goodsId);
        mav.setViewName("goods/goods_show/goods_detail");
        return mav;
    }

    @RequestMapping("add_shopping_cart.do/{buy_count}/{goods_id}")
    public void add_shopping_cart(
            HttpServletRequest request,
            @PathVariable("buy_count") Long buyCount,
            @PathVariable("goods_id") Long goodsId) {
        SecurityContextImpl securityContext = (SecurityContextImpl) request
                .getSession()
                .getAttribute("SPRING_SECURITY_CONTEXT");
        String username = securityContext.getAuthentication().getName();
        Order_form orderForm = new Order_form();
        orderForm.setBuy_number(buyCount);
        orderForm.setGoods_id(goodsId);
        orderForm.setUnit_price(goodsManagerInterface.getGoodsById(goodsId).getPrice());
        orderForm.setUser_id(userManagerInterface.getUserByLoginName(username).getUser_id());
        orderForm.setSolder_id(shopManageInterface.getStoreByStoreId(goodsManagerInterface.getGoodsById(goodsId).getStore_id()).getUser_id());
        orderManagerInterface.addShoppingCart(orderForm);
    }

    @RequestMapping("delete_order/{order_id}")
    public ModelAndView delete_order(@PathVariable("order_id")Long orderId){
        orderManagerInterface.deleteOrder(orderId);
        return new ModelAndView("redirect:/shopping_cart.do");
    }

    @RequestMapping("shopping_cart.do")
    public ModelAndView shopping_cart(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        SecurityContextImpl securityContext = (SecurityContextImpl) request
                .getSession()
                .getAttribute("SPRING_SECURITY_CONTEXT");
        String username = securityContext.getAuthentication().getName();
        List<Order_form> orderLists = orderManagerInterface.getAllStateOrderByUserId(0, userManagerInterface.getUserByLoginName(username).getUser_id());
        List<OrderGoods> orderGoods = new ArrayList<OrderGoods>();
        for (int i = 0; i < orderLists.size(); i++) {
            if (orderLists.get(i) != null && !orderLists.get(i).getPay_state()) {
                OrderGoods tempOrderGoods = new OrderGoods();
                tempOrderGoods.setGoods(goodsManagerInterface.getGoodsById(orderLists.get(i).getGoods_id()));
                tempOrderGoods.setOrder(orderLists.get(i));
                orderGoods.add(tempOrderGoods);
            }
        }
        LoggingUtil.log(orderGoods);
        mav.addObject("orderGoodsList", orderGoods);
        mav.setViewName("buyer/shopping_cart");
        return mav;
    }

    @RequestMapping("confirm_receipt.do/{order_id}")
    public ModelAndView confirm_receipt(@PathVariable("order_id")Long orderId){
        orderManagerInterface.changeShippingState(4,orderId);
        return new ModelAndView("redirect:/order_information.do");
    }

    @RequestMapping("comment_goods.do/{order_id}")
    public ModelAndView comment_goods(@PathVariable("order_id")Long orderId){
        ModelAndView mav = new ModelAndView();
        Long goodsId = orderManagerInterface.getOrderById(orderId).getGoods_id();
        Goods goods = goodsManagerInterface.getGoodsById(goodsId);
        List<Comment_table> commentList = commentManageInterface.getCommentByGoodsId(goodsId);
        mav.addObject("orderId",orderId);
        mav.addObject("commentList",commentList);
        mav.addObject("goods",goods);
        mav.setViewName("goods/goods_show/goods_comment");
        return mav;
    }

    @RequestMapping("submit_comment.do/{order_id}")
    public ModelAndView submit_comment(Comment_table comment,HttpServletRequest request,@PathVariable("order_id")Long orderId){
        ModelAndView mav = new ModelAndView();
        comment.setUsername(UserUtil.getUserName(request));
        comment.setComment_date(new Date());
        orderManagerInterface.changeShippingState(5,orderId);
        commentManageInterface.addComment(comment);
        mav.setViewName("redirect:/order_information.do");
        return mav;
    }
}
