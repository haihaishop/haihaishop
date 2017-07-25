package com.shop.model.service;

/**
 * Created by 18240 on 2017/7/26.
 */
public interface UserAddressManageInterface {
    public static final String cacheName="userAddressCache";

    void addUserAddress(Long userId, Long addressId);

}
