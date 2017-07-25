package com.shop.controller.goods.show;

import com.github.pagehelper.PageInfo;
import com.shop.Utils.LoggingUtil;
import com.shop.model.domain.Cate;
import com.shop.model.domain.Goods;
import com.shop.model.service.CateManagerInterface;
import com.shop.model.service.GoodsManageInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class GoodsShowController {

    @Autowired
    GoodsManageInterface goodsService;
    @Autowired
    CateManagerInterface cateService;

    @RequestMapping("/search")
    public ModelAndView searchGoods(@RequestParam("word")String goodsName,
                                    @RequestParam(value = "page", required = false, defaultValue = "1")int page,
                                    @RequestParam(value = "rows", required = false, defaultValue = "20")int rows)
    throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        List<Cate> cateList = cateService.getAllCates();
        modelAndView.addObject("cateList", cateList);
        String str=new String(goodsName.getBytes("ISO-8859-1"),"UTF-8");
        List<Goods> goodsList = goodsService.searchGoodsByName(str, page, rows);
        LoggingUtil.log(str);
        PageInfo<Goods> pageInfo =new  PageInfo<Goods>(goodsList);
        modelAndView.addObject("goodsList",goodsList);
        modelAndView.addObject("pageInfo", pageInfo);
        modelAndView.setViewName("goods/goods_show/search_result");
        modelAndView.addObject("word", str);
        modelAndView.addObject("page", page);
        return modelAndView;

    }
}
