package com.shop.model.mapper;

import com.shop.model.domain.Order_form;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by 18240 on 2017/7/24.
 */
public interface OrderMapper {

    //加入购物车
    @Insert("insert into order_form (goods_id, user_id, solder_id, buy_number, unit_price)" +
    "values (#{goods_id}, #{user_id}, #{solder_id}, #{buy_number}, #{unit_price})")
    void addShoppingCart(Order_form orderForm);

    @Select("select * from order_form where user_id = #{user_id} and pay_state = 0")
    List<Order_form> getAllOrderByUserId(@Param("user_id")Long UserId);
}
