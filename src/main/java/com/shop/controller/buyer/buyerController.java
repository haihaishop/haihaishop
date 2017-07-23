package com.shop.controller.buyer;

import com.shop.model.domain.Cate;
import com.shop.model.domain.Goods;
import com.shop.model.service.CateManagerInterface;
import com.shop.model.service.GoodsManageInterface;
import org.apache.commons.net.ftp.parser.MacOsPeterFTPEntryParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.ListResourceBundle;

/**
 * Created by 18240 on 2017/7/20.
 */
@Controller
public class buyerController {

    @Autowired
    private CateManagerInterface cateManagerInterface;
    @Autowired
    private GoodsManageInterface goodsManagerInterface;

    @RequestMapping("buyer_home_page.do/{cate_id}")
    public ModelAndView cateClick(@PathVariable("cate_id")int cateId){
        ModelAndView mav = new ModelAndView();
        List<Cate> cateList = cateManagerInterface.getAllCates();
        List<Goods> goodsList = goodsManagerInterface.getAllGoodsByCateId(cateId);
        mav.addObject("cateList", cateList);
        mav.addObject("goodsList",goodsList);
        mav.setViewName("buyer/buyer_home_page");
        return mav;
    }

    @RequestMapping("buyer_home_page.do")
    public ModelAndView buyer_home_page(){
        ModelAndView mav = new ModelAndView();
        List<Cate> cateList = cateManagerInterface.getAllCates();
        mav.addObject("cateList", cateList);
        mav.setViewName("buyer/buyer_home_page");
        return mav;
    }
}
