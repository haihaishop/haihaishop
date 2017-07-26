package com.shop.model.mapper;

import com.shop.model.domain.Address;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by 18240 on 2017/7/26.
 */
public interface AddressMapper {

    @Insert("insert into address(province, city, country, detail_address)" +
            "values(#{province}, #{city}, #{country}, #{detail_address})")
    @Options(keyProperty = "address_id", useGeneratedKeys = true)
    void addAddress(Address address);

    @Select("select * from address where address_id = #{address_id}")
    Address getAddressById(@Param("address_id")Long addressId);
}
