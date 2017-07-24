package com.shop.model.service.Manager;

import com.shop.model.domain.Promotion;
import com.shop.model.mapper.GoodsMapper;
import com.shop.model.mapper.PromotionMapper;
import com.shop.model.service.PromotionManagerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("promotionService")
@Transactional
@CacheConfig(cacheNames = {PromotionManagerInterface.cacheName})
public class PromotionService implements PromotionManagerInterface{
    @Autowired
    PromotionMapper promotionMapper;
    @Autowired
    GoodsMapper goodsMapper;

    public void addPromotion(Promotion promotion, List<Long> goodsIdList) {
        promotionMapper.addPromotion(promotion);
        for (Long goodsId:goodsIdList
             ) {
            goodsMapper.addPromoton(promotion.getPromotion_id(), goodsId);
        }
    }
}
