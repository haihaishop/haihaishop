package com.shop.model.mapper;

import com.shop.model.domain.Role;
import com.shop.model.domain.User;

import org.apache.ibatis.annotations.Param;

import org.apache.ibatis.annotations.Select;

/**
 * Created by 18240 on 2017/7/17.
 */
public interface RoleMapper {

    @Select("select role_id from role where name=#{roleName}")
    Long getRoleIdFromName(@Param("roleName") String roleName);

    @Select("select role_id from role where name=#{role}")
    public int select(Role role);

    @Select("select name from role where role_id=#{roleID}")
    String getNameFromRoleID(@Param("roleID")long roleID);

}
