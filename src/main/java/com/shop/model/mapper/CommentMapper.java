package com.shop.model.mapper;

import com.shop.model.domain.Comment_table;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by 18240 on 2017/7/27.
 */
public interface CommentMapper {
    @Select("select * from comment_table where goods_id = #{goods_id}")
    List<Comment_table> getCommentByGoodsId(@Param("goods_id")Long goodsId);
}
