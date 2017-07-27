package com.shop.model.service.Manager;

import com.shop.model.domain.Comment_table;
import com.shop.model.mapper.CommentMapper;
import com.shop.model.service.CommentManageInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 18240 on 2017/7/27.
 */

@Service("commentManager")
@CacheConfig(cacheNames = {CommentManageInterface.cacheName})

@Transactional
public class CommentService implements CommentManageInterface{
    @Autowired
    CommentMapper commentMapper;

    public List<Comment_table> getCommentByGoodsId(Long goodsId) {
        return commentMapper.getCommentByGoodsId(goodsId);
    }
}
