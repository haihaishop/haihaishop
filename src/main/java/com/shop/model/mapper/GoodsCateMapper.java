package com.shop.model.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.security.access.method.P;

/**
 * Created by 18240 on 2017/7/23.
 */
public interface GoodsCateMapper {

    @Select("select goods_id from goods_cate where cate_id = #{cate_id}")
    int getGoodsIdByCateId(@Param("cate_id")int cateId);

    @Select("select cate_id from goods_cate where goods_id = #{goods_id}")
    int getCateIdByGoodsId(@Param("goods_id")int goodsId);
}
