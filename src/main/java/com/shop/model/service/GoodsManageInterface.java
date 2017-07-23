package com.shop.model.service;

import com.shop.model.domain.Cate;
import com.shop.model.domain.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsManageInterface {
    public static String cacheName = "goods";
    void addGoods(Goods goods, Long[] allCateId);
    List<Goods> getGoodsByStoreId(Long storeId);
    List<Goods> getAllGoodsByCateId(Long cateId);
    List<Cate> getAllCateByGoodsId(Long goodsId);
    Goods getGoodsById(Long goodsId);

}
