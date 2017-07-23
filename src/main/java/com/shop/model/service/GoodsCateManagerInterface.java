package com.shop.model.service;

import org.apache.ibatis.annotations.Param;

/**
 * Created by 18240 on 2017/7/23.
 */
public interface GoodsCateManagerInterface {
    int getCateIdByGoodsId(int goodsId);
    int getGoodsIdByCateId(int cateId);
}
