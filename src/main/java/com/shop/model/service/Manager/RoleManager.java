package com.shop.model.service.Manager;

import com.shop.model.mapper.RoleMapper;
import com.shop.model.service.RoleManagerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 15764 on 2017-07-18.
 */
@Service("roleManager")
@Transactional
@CacheConfig(cacheNames = {RoleManagerInterface.cacheName})
public class RoleManager implements RoleManagerInterface{
    @Autowired
    RoleMapper roleMapper;

    public int getRoleIdFromName(String roleName) {
        return roleMapper.getRoleIdFromName(roleName);
    }
}
