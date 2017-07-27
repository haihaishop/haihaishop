package com.shop.model.mapper;

import com.shop.model.domain.Comment_table;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by 18240 on 2017/7/27.
 */
public interface CommentMapper {
    @Select("select * from comment_table where goods_id = #{goods_id} order by comment_table_id desc")
    List<Comment_table> getCommentByGoodsId(@Param("goods_id")Long goodsId);

    @Insert("insert into comment_table (goods_id, username, comment, rate, comment_date)" +
            "values (#{goods_id}, #{username}, #{comment}, #{rate}, #{comment_date})")
    void addComment(Comment_table comment);
}
