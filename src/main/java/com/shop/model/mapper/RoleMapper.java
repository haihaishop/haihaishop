package com.shop.model.mapper;

import com.shop.model.domain.Role;
import com.shop.model.domain.User;
import org.apache.ibatis.annotations.Select;

/**
 * Created by 18240 on 2017/7/17.
 */
public interface RoleMapper {
    @Select("select role_id from role where name=#{role}")
    public int select(Role role);
}
