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

    @RequestMapping("/add_promotion_post")
    public String addPromotionPost(Promotion promotion,
                                   @RequestParam(value = "goods[]",required = false)List<Long> goodsIdList,
                                   RedirectAttributes model){
        promotionService.addPromotion(promotion, goodsIdList);
        model.addFlashAttribute("success", "添加成功");
        return "redirect:/shop_admin/shop";
    }

    @RequestMapping("/{store_id}edit_promotion")
    public ModelAndView editPromotionPage(@PathVariable("store_id")Long storeId,
                                      HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("shop/shop_admin/shop_edit_promotion");
        List<Promotion> promotions = promotionService.getPromotionsByStoreId(storeId);
        modelAndView.addObject("promotions", promotions);
        Store store = shopService.getStoreByUsername(getUserName(request));
        modelAndView.addObject("store", store);
        return modelAndView;
    }

    @RequestMapping("/{promotion_id}_edit_promotion")
    public ModelAndView editPromotion(@PathVariable("promotion_id")Long promotionId,
                                      HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        Promotion promotion = promotionService.getPromotionById(promotionId);
        modelAndView.addObject("promotion", promotion);
        List<Goods> goodsList = goodsService.getGoodsByStoreId(promotion.getStore_id());
        modelAndView.addObject("allGoods", goodsList);
        List<Goods> goodsPromotions = promotionService.getGoodsByPromotionId(promotionId);
        modelAndView.addObject("goodsPromotions", goodsPromotions);
        Store store = shopService.getStoreByUsername(getUserName(request));
        modelAndView.addObject("store", store);
        modelAndView.setViewName("promotion/edit_promotion");
        return modelAndView;
    }

    @RequestMapping("/edit_promotion_post")
    public String editPromotionPost(Promotion promotion,
                                    @RequestParam(value = "goods[]",required = false)List<Long> goodsIdList,
                                    RedirectAttributes model){
        promotionService.changePromotion(promotion, goodsIdList);
        model.addFlashAttribute("success", "修改成功！");
        return "redirect:/shop_admin/shop";
    }

    @RequestMapping("/{promotion_id}_delete_promotion")
    public String deletePromotion(@PathVariable("promotion_id")Long promotionId,
                                  RedirectAttributes model){
        promotionService.deletePromotion(promotionId);
        model.addFlashAttribute("success","删除成功!");
        return "redirect:/shop_admin/shop";
    }
}
