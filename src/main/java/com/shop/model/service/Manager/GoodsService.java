package com.shop.model.service.Manager;

import com.shop.model.domain.Goods;
import com.shop.model.mapper.GoodsMapper;
import com.shop.model.service.GoodsManageInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("goodsService")
@CacheConfig(cacheNames = {GoodsManageInterface.cacheName})
@Transactional
public class GoodsService implements GoodsManageInterface{

    @Autowired
    GoodsMapper goodsMapper;

    @CacheEvict(allEntries = true)
    public void addGoods(Goods goods, Long[] allCateId) {
        Long goodsId = goodsMapper.addGoods(goods);
        for (Long cateId:allCateId) {
            goodsMapper.addCateToGoods(goodsId, cateId);
        }
    }

    @Cacheable(key = "#root.methodName+#root.args[0]")
    public List<Goods> getGoodsByStoreId(Long storeId) {
        return goodsMapper.getGoodsByStoreId(storeId);
    }
}
