package com.shop.model.mapper;

import com.shop.model.domain.Cate;
import com.shop.model.domain.Goods;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by 18240 on 2017/7/23.
 */
public interface GoodsMapper {

    @Select("select * from goods where goods_id in (select goods_id from goods_cate where cate_id = #{cate_id})")
    List<Goods> getAllGoodsByCateId(@Param("cate_id")int cateId);

    @Select("select * from cate where cate_id in (select cate_id from goods_cate where goods_id = #{goods_id})")
    List<Cate> getAllCateByGoodsId(@Param("goods_id")int goodsId);

}
