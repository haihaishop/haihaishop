package com.shop.model.service;

import com.shop.model.domain.Address;

/**
 * Created by 18240 on 2017/7/26.
 */
public interface AddressManageInterface {
    public static String cacheName = "addressCache";

    void addAddress(Address address);
    Address getAddressById(Long addressId);
}
