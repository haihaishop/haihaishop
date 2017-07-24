package com.shop.model.service;

import com.shop.model.domain.Order_form;

/**
 * Created by 18240 on 2017/7/24.
 */
public interface OrderManagerInterface {
    public static String cacheName = "orderCache";

    void addShoppingCart(Order_form orderForm);
}
