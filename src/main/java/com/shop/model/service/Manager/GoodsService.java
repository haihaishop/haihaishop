package com.shop.model.service.Manager;

import com.shop.model.domain.Cate;
import com.shop.model.domain.Goods;
import com.shop.model.mapper.CateMapper;
import com.shop.model.mapper.GoodsCateMapper;
import com.shop.model.mapper.GoodsMapper;
import com.shop.model.service.GoodsManagerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 18240 on 2017/7/23.
 */

@Service("goodsManager")
@Transactional
public class GoodsService implements GoodsManagerInterface{
    @Autowired
    private GoodsMapper goodsMapper;

    public List<Goods> getAllGoodsByCateId(int cateId) {
        return goodsMapper.getAllGoodsByCateId(cateId);
    }

    public List<Cate> getAllCateByGoodsId(int goodsId) {
        return goodsMapper.getAllCateByGoodsId(goodsId);
    }
}
