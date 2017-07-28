package com.shop.model.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.RequestMapping;
import com.shop.model.domain.Cate;
import com.shop.model.domain.Goods;

import java.util.List;

public interface GoodsMapper {

    @Insert("insert into goods(goods_name, store_id, price, " +
            "info, date, count, picture, promotion_id)" +
            " values (#{goods_name}, #{store_id}, #{price}, " +
            "#{info}, #{date}, #{count}, #{picture}, #{promotion_id})")
    @Options(keyProperty="goods_id", useGeneratedKeys=true )
    Long addGoods(Goods goods);

    @Insert("insert into goods_cate(goods_id, cate_id) values(#{goods_id}, #{cate_id})")
    void addCateToGoods(@Param("goods_id")Long goodsId,
                        @Param("cate_id")Long cateId);

    @Select("select * from goods where store_id=#{store_id} " +
            "Order By sold_number,views_time Desc")
    List<Goods> getGoodsByStoreId(@Param("store_id") Long storeId);

    @Select("select * from goods where goods_id in (select goods_id " +
            "from goods_cate where cate_id = #{cate_id}) and" +
            "  store_id in (select store_id from store where store_status = 1)" +
            " Order By sold_number,views_time Desc")
    List<Goods> getAllGoodsByCateId(@Param("cate_id")Long cateId);

    @Select("select * from cate where cate_id in (select cate_id " +
            "from goods_cate where goods_id = #{goods_id})")
    List<Cate> getAllCateByGoodsId(@Param("goods_id")Long goodsId);

    @Select("select * from goods where goods_id = #{goods_id}")
    Goods getGoodsById(@Param("goods_id")Long goodsId);

    @Update("update goods set sold_number = #{sold_number}, goods_name=#{goods_name}," +
            "price=#{price},info=#{info},count=#{count},picture=#{picture}," +
            "promotion_id=#{promotion_id} " +
            "where goods_id=#{goods_id}")
    void changeGoods(Goods goods);

    @Delete("delete from goods_cate where goods_id=#{goods_id}")
    void deleteGoodsAllCate(@Param("goods_id")Long goodsId);

    @Delete("delete from goods where goods_id=#{goods_id}")
    void deleteGoods(@Param("goods_id")Long goodsId);

    @Update("update goods set promotion_id=#{promotion_id} where goods_id=#{goods_id}")
    void addPromotion(@Param("promotion_id")Long promotionId,
                     @Param("goods_id")Long goods_id);

    @Update("update goods set views_time=views_time+1 where goods_id=#{goods_id}")
    void increaseViewsTime(@Param("goods_id") Long goodsId);

    @Select("select * from goods where goods_name like CONCAT('%','${goods_name}','%' )" +
            " and  store_id in (select store_id from store where store_status = 1)")
    List<Goods> searchGoodsByName(@Param("goods_name")String goodsName);

    @Select("select * from goods" +
            " where store_id in (select store_id from store where store_status = 1)  " +
            "Order By sold_number,views_time Desc")
    List<Goods> getAllGoods();

    @Select("select * from goods where store_id=#{store_id} and " +
            "goods_id in " +
            "(select goods_id from goods_cate where cate_id = #{cate_id}) " +
            "Order By sold_number,views_time Desc")
    List<Goods> getGoodsByStoreIdAndCateId(@Param("store_id") Long storeId,
                                  @Param("cate_id") Long cateId);

    @Select("select * from goods " +
            "where goods_name like CONCAT('%','${goods_name}','%' ) " +
            "and store_id=#{store_id}")
    List<Goods> searchGoodsByNameAndStoreId(@Param("goods_name")String goodsName,
                                            @Param("store_id")Long storeId);
}