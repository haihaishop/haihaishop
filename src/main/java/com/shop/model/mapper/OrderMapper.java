package com.shop.model.mapper;

import com.shop.model.domain.Order_form;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * Created by 18240 on 2017/7/24.
 */
public interface OrderMapper {

    //加入购物车
    @Insert("insert into order_form (goods_id, user_id, solder_id, buy_number, date)" +
    "values (#{goods_id}, #{user_id}, #{solder_id}, #{buy_number}, #{date})")
    void addShoppingCart(Order_form orderForm);
}
