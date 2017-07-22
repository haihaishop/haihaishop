package com.shop.model.service.Manager;

import com.shop.model.domain.Store;
import com.shop.model.domain.User;
import com.shop.model.mapper.ShopMapper;
import com.shop.model.mapper.UserMapper;
import com.shop.model.service.ShopManageInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("shopService")
@Transactional
@CacheConfig(cacheNames = {ShopManageInterface.cacheName})
public class ShopService implements ShopManageInterface{
    @Autowired
    ShopMapper shopMapper;
    @Autowired
    UserMapper userMapper;

    @CacheEvict(allEntries = true)
    public void addShop(Store shop, String username) {
       User user = userMapper.getUserByLoginName(username);
       shop.setUser_id(user.getUser_id());
       shopMapper.addStore(shop);
    }

    @Cacheable(key = "#root.methodName+#root.args[0]")
    public boolean hasShop(String shopName) {
        Store shop = shopMapper.getStoreByName(shopName);
        return shop != null;
    }

    @Cacheable(key = "#root.methodName+#root.args[0]")
    public Store getStoreByUsername(String username) {
        User user = userMapper.getUserByLoginName(username);
        Store store = shopMapper.getStoreByUserId(user.getUser_id());
        return store;
    }
}
