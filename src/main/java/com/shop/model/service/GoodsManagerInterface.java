package com.shop.model.service;

import com.shop.model.domain.Cate;
import com.shop.model.domain.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 18240 on 2017/7/23.
 */
public interface GoodsManagerInterface {
    List<Goods> getAllGoodsByCateId(int cateId);
    List<Cate> getAllCateByGoodsId(int goodsId);
}
