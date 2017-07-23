package com.shop.model.service.Manager;

import com.shop.model.mapper.GoodsCateMapper;
import com.shop.model.mapper.GoodsMapper;
import com.shop.model.service.GoodsCateManagerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 18240 on 2017/7/23.
 */

@Service("goodsCateManager")
@Transactional
public class GoodsCateService implements GoodsCateManagerInterface{

    @Autowired
    private GoodsCateMapper goodsCateMapper;

    public int getGoodsIdByCateId(int cateId) {
        return goodsCateMapper.getGoodsIdByCateId(cateId);
    }

    public int getCateIdByGoodsId(int goodsId) {
        return goodsCateMapper.getCateIdByGoodsId(goodsId);
    }
}
