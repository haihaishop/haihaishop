package com.shop.model.mapper;

import com.shop.model.domain.Store;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.jms.annotation.JmsListener;

public interface ShopMapper {

    @Insert("insert into store(user_id, store_name, store_info, create_date, store_status, image)" +
            "values (#{user_id}, #{store_name}, #{store_info}, #{create_date}, #{store_status}, #{image})")
    void addStore(Store store);

    @Select("select * from store where store_name=#{store_name}")
    Store getStoreByName(@Param("store_name")String storeName);

    @Select("select * from store where user_id=#{user_id}")
    Store getStoreByUserId(@Param("user_id")Long userId);

    @Update("update store set store_name=#{store_name}, store_info=#{store_info}, store_status=#{store_status}, image=#{image}" +
            " where store_id=#{store_id}")
    void changeStore(Store store);

    @Select("select * from store where store_id = #{store_id}")
    Store getStoreByStoreId(Long storeId);

    @Update("update store set store_status=#{store_status} where store_id=#{store_id}")
    void changeStatus(@Param("store_status")boolean storeStatus,
                      @Param("store_id")Long storeId);

}
