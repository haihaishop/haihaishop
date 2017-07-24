package com.shop.controller.promotion;

import com.shop.Utils.LoggingUtil;
import com.shop.model.domain.Goods;
import com.shop.model.domain.Promotion;
import com.shop.model.domain.Store;
import com.shop.model.service.GoodsManageInterface;
import com.shop.model.service.PromotionManagerInterface;
import com.shop.model.service.ShopManageInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static com.shop.Utils.UserUtil.getUserName;

@Controller
@RequestMapping("/shop_admin")
public class PromotionController {
    @Autowired
    ShopManageInterface shopService;
    @Autowired
    GoodsManageInterface goodsService;
    @Autowired
    PromotionManagerInterface promotionService;

    @RequestMapping("/{store_id}add_promotion")
    public ModelAndView addPromotion(@PathVariable("store_id")Long storeId,
                                     HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("store_id", storeId);
        Store store = shopService.getStoreByUsername(getUserName(request));
        modelAndView.addObject("store", store);
        List<Goods> goodsList = goodsService.getGoodsByStoreId(storeId);
        modelAndView.addObject("allGoods", goodsList);
        modelAndView.setViewName("promotion/add_promotion");
        return modelAndView;
    }

    @RequestMapping("/{store_id}add_promotion_post")
    public String addPromotionPost(Promotion promotion,
                                   @RequestParam("goods[]")List<Long> goodsIdList,
                                   RedirectAttributes model){
        promotionService.addPromotion(promotion, goodsIdList);
        //判断活动重复？
        return "redirect:/shop_admin/shop";
    }
}
