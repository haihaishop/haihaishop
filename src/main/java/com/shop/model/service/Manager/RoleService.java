package com.shop.model.service.Manager;

import com.shop.model.mapper.RoleMapper;
import com.shop.model.service.RoleManagerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 15764 on 2017-07-18.
 */
@Service("roleService")
@Transactional
@CacheConfig(cacheNames = {RoleManagerInterface.cacheName})
public class RoleService implements RoleManagerInterface{
    @Autowired
    RoleMapper roleMapper;

    @Cacheable(key = "#root.methodName+#root.args[0]")
    public Long getRoleIdFromName(String roleName) {
        return roleMapper.getRoleIdFromName(roleName);
    }

    @Cacheable(key = "#root.methodName+#root.args[0]")
    public String getNameFromRoleId(long roleId) {
        return roleMapper.getNameFromRoleID(roleId);
    }
}
