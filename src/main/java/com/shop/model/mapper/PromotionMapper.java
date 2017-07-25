package com.shop.model.mapper;

import com.shop.model.domain.Goods;
import com.shop.model.domain.Promotion;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface PromotionMapper {

    @Insert("insert into " +
            "promotion(name, type, discount, full, cut, buy, give, from_time, " +
            "to_time, store_id, is_all_site) " +
            "values(#{name}, #{type}, #{discount}, #{full}, #{cut}, #{buy}, #{give}," +
            " #{from_time}, #{to_time}, #{store_id}, #{is_all_site})")
    @Options(keyProperty = "promotion_id", useGeneratedKeys = true)
    void addPromotion(Promotion promotion);

    @Select("select * from promotion where store_id=#{store_id}")
    List<Promotion> getPromotionsByStoreId(@Param("store_id")Long storeId);

    @Select("select * from goods where promotion_id=#{id}")
    List<Goods> getGoodsByPromotionId(@Param("id")Long promotionId);

    @Select("select * from promotion where promotion_id=#{id}")
    Promotion getPromotionById(@Param("id")Long promotionId);

    @Update("update promotion set name=#{name},type=#{type},discount=#{discount}," +
            "full=#{full},cut=#{cut},buy=#{buy},give=#{give},from_time=#{from_time}," +
            "to_time=#{to_time},store_id=#{store_id},is_all_site=#{is_all_site} " +
            "where promotion_id=#{promotion_id} ")
    void changePromotion(Promotion promotion);

    @Select("select * from promotion where is_all_site=b'1'")
    List<Promotion> getAllSitePromotions();

    @Delete("delete from promotion where promotion_id=#{promotion_id}")
    void deletePromotion(@Param("promotion_id")Long promotionId);

    @Update("update goods set promotion_id=NULL where promotion_id=#{promotion_id}")
    void clearPromotion(@Param("promotion_id")Long promotionId);

}
