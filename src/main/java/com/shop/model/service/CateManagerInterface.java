package com.shop.model.service;

import com.shop.model.domain.Cate;

import java.util.List;

public interface CateManagerInterface {
    public static String cacheName = "cateCache";

    List<Cate> getAllCates();
    boolean hasCate(String cateName);
    void addCate(Cate cate);
    Cate getCateById(Long cateId);
    void changeCateName(Cate cate);
    void deleteCate(Long cate_id);
    int getCateIdByCateName(String cateName);

}
