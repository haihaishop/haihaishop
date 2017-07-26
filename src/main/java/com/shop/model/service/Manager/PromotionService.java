package com.shop.model.service.Manager;

import com.shop.model.domain.Goods;
import com.shop.model.domain.Promotion;
import com.shop.model.mapper.GoodsMapper;
import com.shop.model.mapper.PromotionMapper;
import com.shop.model.service.PromotionManagerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

    @CacheEvict(allEntries = true)
    public void addPromotion(Promotion promotion, List<Long> goodsIdList) {
        promotionMapper.addPromotion(promotion);
        if (goodsIdList != null) {
            for (Long goodsId : goodsIdList
                    ) {
                goodsMapper.addPromotion(promotion.getPromotion_id(), goodsId);
            }
        }
    }

    public List<Promotion> getPromotionsByStoreId(Long storeId) {
        return promotionMapper.getPromotionsByStoreId(storeId);
    }

    public List<Goods> getGoodsByPromotionId(Long promotionId) {
        return promotionMapper.getGoodsByPromotionId(promotionId);
    }

    @Cacheable(key = "#root.methodName+#root.args[0]")
    public Promotion getPromotionById(Long promotionId) {
        return promotionMapper.getPromotionById(promotionId);
    }

    @Cacheable(key = "#root.methodName+#root.args[0]")
    public List<Promotion> getPromotions(Long storeId) {
        List<Promotion> allSitePromotions = promotionMapper.getAllSitePromotions();
        List<Promotion> shopPromotions = promotionMapper.getPromotionsByStoreId(storeId);
        allSitePromotions.addAll(shopPromotions);
        return allSitePromotions;
    }

    @CacheEvict(allEntries = true)
    public void changePromotion(Promotion promotion, List<Long> goodsIdList) {
        promotionMapper.clearPromotion(promotion.getPromotion_id());
        promotionMapper.changePromotion(promotion);
        if (goodsIdList != null) {
            for (Long goodsId : goodsIdList
                    ) {
                goodsMapper.addPromotion(promotion.getPromotion_id(), goodsId);
            }
        }
    }

    @CacheEvict(allEntries = true)
    public void deletePromotion(Long promotionId) {
        promotionMapper.clearPromotion(promotionId);
        promotionMapper.deletePromotion(promotionId);
    }

    public List<Promotion> getAllSitePromotions() {
        return promotionMapper.getAllSitePromotions();
    }
}
