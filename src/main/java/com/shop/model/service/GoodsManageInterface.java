package com.shop.model.service;

import com.shop.model.domain.Cate;
import com.shop.model.domain.Goods;

import java.util.List;

public interface GoodsManageInterface {
    public static String cacheName = "goods";
    void addGoods(Goods goods, Long[] allCateId);
    List<Goods> getGoodsByStoreId(Long storeId);
    List<Goods> getAllGoodsByCateId(int cateId);
    List<Cate> getAllCateByGoodsId(int goodsId);
}
