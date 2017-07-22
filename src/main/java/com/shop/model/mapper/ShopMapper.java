package com.shop.model.mapper;

import com.shop.model.domain.Store;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ShopMapper {

    @Insert("insert into store(user_id, store_name, store_info, create_date, store_status, image)" +
            "values (#{user_id}, #{store_name}, #{store_info}, #{create_date}, #{store_status}, #{image})")
    void addStore(Store store);

    @Select("select * from store where store_name=#{store_name}")
    Store getStoreByName(@Param("store_name")String storeName);

    @Select("select * from store where user_id=#{user_id}")
    Store getStoreByUserId(@Param("user_id")Long userId);

}
