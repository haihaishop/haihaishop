package com.shop.model.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * Created by 18240 on 2017/7/26.
 */
public interface UserAddressMapper {

    @Insert("insert into user_address(user_id, address_id) values (#{user_id}, #{address_id})")
    void addUserAddress(@Param("user_id")Long userId,@Param("address_id")Long addressId);
}
