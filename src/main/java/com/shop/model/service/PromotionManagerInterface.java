package com.shop.model.service;

import com.shop.model.domain.Goods;
import com.shop.model.domain.Promotion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PromotionManagerInterface {
    public static String cacheName = "promotion";
    void addPromotion(Promotion promotion, List<Long> goodsIdList);
    List<Promotion> getPromotionsByStoreId(Long storeId);
    List<Goods> getGoodsByPromotionId(Long promotionId);
    Promotion getPromotionById(Long promtionId);
    List<Promotion> getPromotions(Long storeId);
    void changePromotion(Promotion promotion, List<Long> goodsIdList);
    void deletePromotion(Long promotionId);
    List<Promotion> getAllSitePromotions();
}
