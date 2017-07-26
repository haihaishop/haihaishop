package com.shop.model.service;

import com.shop.model.domain.Order_form;

import java.util.List;

/**
 * Created by 18240 on 2017/7/24.
 */
public interface OrderManagerInterface {
    public static String cacheName = "orderCache";

    void addShoppingCart(Order_form orderForm);
    List<Order_form> getAllStateOrderByUserId(int shippingState, Long userId);
    List<Order_form> getAllOrderByUserId(Long userId);
    Order_form getOrderById(Long orderFormId);
    void changePayState(boolean payState,Long addressId, Long userId);
    void changePlaceState(boolean placeOrder, Long orderId);
    void changeShippingState(int shippingState, Long orderId);

}
