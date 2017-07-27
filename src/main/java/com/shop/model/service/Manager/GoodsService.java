package com.shop.model.service.Manager;

import com.github.pagehelper.PageHelper;
import com.shop.Utils.LoggingUtil;
import com.shop.model.domain.Cate;
import com.shop.model.domain.Goods;
import com.shop.model.domain.Store;
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
        goodsMapper.addGoods(goods);
        if( allCateId != null) {
            for (Long cateId : allCateId) {
                goodsMapper.addCateToGoods(goods.getGoods_id(), cateId);
            }
        }
    }

    public void changeGoods(Goods goods) {
        goodsMapper.changeGoods(goods);
    }

    @Cacheable(key = "#root.methodName+#root.args[0]")
    public List<Goods> getGoodsByStoreId(Long storeId) {
        return goodsMapper.getGoodsByStoreId(storeId);
    }

    public List<Goods> getGoodsByStoreId(Long storeId, int page, int rows) {
        PageHelper.startPage(page, rows);
        return goodsMapper.getGoodsByStoreId(storeId);
    }

    public List<Goods> getAllGoodsByCateId(Long cateId, int page, int rows) {
        PageHelper.startPage(page, rows);
        return goodsMapper.getAllGoodsByCateId(cateId);
    }

    @Cacheable(key = "#root.methodName+#root.args[0]")
    public List<Cate> getAllCateByGoodsId(Long goodsId) {
        return goodsMapper.getAllCateByGoodsId(goodsId);
    }

    @Cacheable(key = "#root.methodName+#root.args[0]")
    public Goods getGoodsById(Long goodsId) {
        return goodsMapper.getGoodsById(goodsId);
    }

    @CacheEvict(allEntries = true)
    public void changeGoods(Goods goods, Long[] allCateId) {
        goodsMapper.deleteGoodsAllCate(goods.getGoods_id());
        goodsMapper.changeGoods(goods);
        for (Long cateId:allCateId) {
            goodsMapper.addCateToGoods(goods.getGoods_id(), cateId);
        }
    }

    @CacheEvict(allEntries = true)
    public void deleteGoods(Long goodsId) {
        goodsMapper.deleteGoodsAllCate(goodsId);
        goodsMapper.deleteGoods(goodsId);
    }

    @CacheEvict(allEntries = true)
    public void increaseViewsTime(Long goodsId) {
        goodsMapper.increaseViewsTime(goodsId);
    }

    public List<Goods> searchGoodsByName(String goodsName, int page, int rows) {
        PageHelper.startPage(page, rows);
        LoggingUtil.log(goodsName);
        return goodsMapper.searchGoodsByName(goodsName);
    }

    public List<Goods> getAllGoods(int page, int rows) {
        PageHelper.startPage(page, rows);
        return goodsMapper.getAllGoods();
    }

    public List<Goods> getGoodsByStoreIdAndCateId(Long storeId, Long cateId, int page, int rows) {
        PageHelper.startPage(page, rows);
        return goodsMapper.getGoodsByStoreIdAndCateId(storeId, cateId);
    }

    public List<Goods> searchGoodsByNameAndStoreId(String goodsName, Long storeId, int page, int rows) {
        PageHelper.startPage(page, rows);
        return goodsMapper.searchGoodsByNameAndStoreId(goodsName, storeId);
    }
}
