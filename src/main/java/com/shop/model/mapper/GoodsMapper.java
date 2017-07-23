package com.shop.model.mapper;

import com.shop.model.domain.Goods;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface GoodsMapper {

    @Insert("insert into goods(goods_name, store_id, price, info, date, count, picture)" +
            " values (#{goods_name}, #{store_id}, #{price}, #{info}, #{date}, #{count}, #{picture})")
    @SelectKey(statement ="SELECT LAST_INSERT_ID() AS goods_id", resultType=Long.class, before=false,  keyProperty="goods_id")
    Long addGoods(Goods goods);

    @Insert("insert into goods_cate(goods_id, cate_id) values(#{goods_id}, #{cate_id})")
    void addCateToGoods(@Param("goods_id")Long goodsId,
                        @Param("cate_id")Long cateId);

    @Select("select * from goods where store_id=#{store_id}")
    List<Goods> getGoodsByStoreId(Long storeId);
}
