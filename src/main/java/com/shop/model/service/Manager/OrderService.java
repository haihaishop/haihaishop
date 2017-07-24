package com.shop.model.service.Manager;

import com.shop.model.domain.Order_form;
import com.shop.model.mapper.OrderMapper;
import com.shop.model.service.OrderManagerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 18240 on 2017/7/24.
 */
@Service("orderService")
@CacheConfig(cacheNames = {OrderManagerInterface.cacheName})
@Transactional
public class OrderService implements OrderManagerInterface{
    @Autowired
    private OrderMapper orderMapper;

    public void addShoppingCart(Order_form orderForm) {
        orderMapper.addShoppingCart(orderForm);
    }

    public List<Order_form> getAllOrderByUserId(Long userId) {
        List<Order_form> orderList = orderMapper.getAllOrderByUserId(userId);
        return orderList;
    }
}
