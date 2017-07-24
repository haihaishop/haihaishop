package com.shop.model.mapper;

import com.shop.model.domain.Promotion;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

public interface PromotionMapper {

    @Insert("insert into " +
            "promotion(name, type, discount, full, cut, buy, give, from_time, " +
            "to_time, store_id, is_all_site) " +
            "values(#{name}, #{type}, #{discount), #{full}, #{cut}, #{buy}, #{give}," +
            " #{from_time}, #{to_time}, #{store_id}, #{is_all_site})")
    @Options(keyProperty = "promotion_id", useGeneratedKeys = true)
    Long addPromotion(Promotion promotion);
}
