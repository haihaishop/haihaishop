package com.shop.model.service;

import com.shop.model.domain.Comment_table;

import java.util.List;

/**
 * Created by 18240 on 2017/7/27.
 */
public interface CommentManageInterface {
    public static String cacheName = "commentCache";

    List<Comment_table> getCommentByGoodsId(Long goodsId);

}
