package com.shop.model.mapper;

import com.shop.model.domain.Order_form;
import org.apache.ibatis.annotations.*;
import org.springframework.security.access.method.P;

import java.util.Date;
import java.util.List;

/**
 * Created by 18240 on 2017/7/24.
 */
public interface OrderMapper {

    //加入购物车
    @Insert("insert into order_form (goods_id, user_id, solder_id, buy_number, unit_price)" +
    "values (#{goods_id}, #{user_id}, #{solder_id}, #{buy_number}, #{unit_price})")
    void addShoppingCart(Order_form orderForm);

    @Select("select * from order_form where shipping_state = #{shipping_state} and user_id = #{user_id}")
    List<Order_form> getAllStateOrderByUserId(@Param("shipping_state")int shippingState,@Param("user_id")Long UserId);

    @Select("select * from order_form where user_id = #{user_id} order by order_form_id desc")
    List<Order_form> getAllOrderByUserId(@Param("user_id")Long userId);

    @Select("select * from order_form where solder_id = #{solder_id}  order by order_form_id desc")
    List<Order_form> getAllOrderBySolderId(@Param("solder_id")Long solderId);

    @Select("select * from order_form where order_form_id = #{order_form_id}  order by order_form_id desc")
    Order_form getOrderById(Long orderFormId);

    @Update("update order_form set pay_state = #{pay_state}, address_id = #{address_id} where user_id = #{user_id} and place_order = true")
    void changePayState(@Param("pay_state")boolean payState,
                        @Param("address_id")Long addressId,
                        @Param("user_id")Long userId);

    @Update("update order_form set place_order = #{place_order} where order_form_id = #{order_form_id}")
    void changePlaceState(@Param("place_order")boolean placeOrder,
                        @Param("order_form_id")Long orderId);

    @Update("update order_form set shipping_state = #{shipping_state} where order_form_id = #{order_form_id}")
    void changeShippingState(@Param("shipping_state")int shippingState,
                          @Param("order_form_id")Long orderId);

    @Update("update order_form set address_id = #{address_id},date = #{date} where order_form_id = #{order_form_id}")
    void updateAddressId(@Param("address_id")Long addressId, @Param("date")Date date, @Param("order_form_id")Long orderId);

    @Delete("delete from order_form where order_form_id = #{order_form_id}")
    void deleteOrder(@Param("order_form_id")Long orderId);
}
