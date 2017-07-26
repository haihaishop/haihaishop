package com.shop.model.service.Manager;

import com.shop.model.mapper.UserAddressMapper;
import com.shop.model.service.UserAddressManageInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 18240 on 2017/7/26.
 */
@Service("userAddressService")
@Transactional
@CacheConfig(cacheNames = {UserAddressManageInterface.cacheName})
public class UserAddressService implements UserAddressManageInterface{
    @Autowired
    private UserAddressMapper userAddressMapper;

    public void addUserAddress(Long userId, Long addressId) {
        userAddressMapper.addUserAddress(userId, addressId);
    }
}
