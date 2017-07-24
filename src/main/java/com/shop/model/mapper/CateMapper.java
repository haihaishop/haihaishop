package com.shop.model.mapper;

import com.shop.model.domain.Cate;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CateMapper {

    @Select("select * from cate")
    List<Cate> getAllCates();

    @Insert("insert into cate(cate_name) value(#{cate_name})")
    void addCate(@Param("cate_name")String cateName);

    @Select("select * from cate where cate_name=#{cate_name}")
    Cate getCateByName(@Param("cate_name")String cateName);

    @Select("select * from cate where cate_id=#{cate_id}")
    Cate getCateById(@Param("cate_id")Long cateId);

    @Select("select cate-id from cate where cate_name=#{cate_name}")
    int getCateIdByCateName(@Param("cate_name")String cateName);

    @Update("update cate set cate_name=#{cate_name} where cate_id=#{cate_id}")
    void changeCateName(Cate cate);

    @Delete("delete from cate where cate_id=#{cate_id}")
    void deleteCate(Long cate_id);

    @Delete("delete from goods_cate where cate_id=#{cate_id}")
    void deleteCateFromGoodsCate(Long cate_id);
}
