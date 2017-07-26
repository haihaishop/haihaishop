package com.shop.model.service.Manager;

import com.shop.model.domain.Address;
import com.shop.model.mapper.AddressMapper;
import com.shop.model.service.AddressManageInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 18240 on 2017/7/26.
 */
@Service("addressManager")
@Transactional
@CacheConfig(cacheNames = {AddressManageInterface.cacheName})
public class AddressService implements AddressManageInterface{
    @Autowired
    AddressMapper addressMapper;

    public void addAddress(Address address) {
        addressMapper.addAddress(address);
    }

    public Address getAddressById(Long addressId) {
        return addressMapper.getAddressById(addressId);
    }
}
