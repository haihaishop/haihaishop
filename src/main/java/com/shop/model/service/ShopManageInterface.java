package com.shop.model.service;

import com.shop.model.domain.Store;

public interface ShopManageInterface {
    public static String cacheName = "shop";

    void addShop(Store shop, String username);
    boolean hasShop(String shopName);
    Store getStoreByUsername(String username);
}
