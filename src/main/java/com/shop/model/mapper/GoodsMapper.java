package com.shop.model.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestMapping;
import com.shop.model.domain.Cate;
import com.shop.model.domain.Goods;

import java.util.List;

public interface GoodsMapper {

    @Insert("insert into goods(goods_name, store_id, price, info, date, count, picture)" +
            " values (#{goods_name}, #{store_id}, #{price}, #{info}, #{date}, #{count}, #{picture})")
    @Options(keyProperty="goods_id", useGeneratedKeys=true )
    Long addGoods(Goods goods);

    @Insert("insert into goods_cate(goods_id, cate_id) values(#{goods_id}, #{cate_id})")
    void addCateToGoods(@Param("goods_id")Long goodsId,
                        @Param("cate_id")Long cateId);

    @Select("select * from goods where store_id=#{store_id}")
    List<Goods> getGoodsByStoreId(Long storeId);

    @Select("select * from goods where goods_id in (select goods_id from goods_cate where cate_id = #{cate_id})")
    List<Goods> getAllGoodsByCateId(@Param("cate_id")Long cateId);

    @Select("select * from cate where cate_id in (select cate_id from goods_cate where goods_id = #{goods_id})")
    List<Cate> getAllCateByGoodsId(@Param("goods_id")Long goodsId);

    @Select("select * from goods where goods_id = #{goods_id}")
    Goods getGoodsById(@Param("goods_id")Long goodsId);
}