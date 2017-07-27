package com.shop.model.mapper;

import com.shop.model.domain.User_address;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.security.access.method.P;

import java.util.List;

/**
 * Created by 18240 on 2017/7/26.
 */
public interface UserAddressMapper {

    @Insert("insert into user_address(user_id, address_id) values (#{user_id}, #{address_id})")
    void addUserAddress(@Param("user_id")Long userId,@Param("address_id")Long addressId);

    @Select("select * from user_address where user_id = #{user_id]")
    List<User_address> getAddressList(@Param("user_id")Long userId);
}
