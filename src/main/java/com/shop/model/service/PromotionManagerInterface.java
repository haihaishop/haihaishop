package com.shop.model.service;

import com.shop.model.domain.Promotion;

import java.util.List;

public interface PromotionManagerInterface {
    public static String cacheName = "promotion";
    void addPromotion(Promotion promotion, List<Long> goodsIdList);
}
