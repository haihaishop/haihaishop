package com.shop.model.service;

import com.shop.model.domain.User_address;

import java.util.List;

/**
 * Created by 18240 on 2017/7/26.
 */
public interface UserAddressManageInterface {
    public static final String cacheName="userAddressCache";

    void addUserAddress(Long userId, Long addressId);
    List<User_address> getAddressList(Long userId);

}
