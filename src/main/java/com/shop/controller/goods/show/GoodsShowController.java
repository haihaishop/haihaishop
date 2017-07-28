package com.shop.controller.goods.show;

import com.github.pagehelper.PageInfo;
import com.shop.Utils.LoggingUtil;
import com.shop.Utils.UserUtil;
import com.shop.model.domain.Cate;
import com.shop.model.domain.Goods;
import com.shop.model.domain.Store;
import com.shop.model.domain.User;
import com.shop.model.service.CateManagerInterface;
import com.shop.model.service.GoodsManageInterface;
import com.shop.model.service.ShopManageInterface;
import com.shop.model.service.UserManagerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class GoodsShowController {

    @Autowired
    GoodsManageInterface goodsService;
    @Autowired
    CateManagerInterface cateService;

    @Autowired
    ShopManageInterface shopService;

    @Autowired
    UserManagerInterface userService;

    @RequestMapping("/search")
    public ModelAndView searchGoods(@RequestParam("word")String goodsName,
                                    @RequestParam(value = "page", required = false, defaultValue = "1")int page,
                                    @RequestParam(value = "rows", required = false, defaultValue = "20")int rows)
    throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        List<Cate> cateList = cateService.getAllCates();
        String str;
        modelAndView.addObject("cateList", cateList);
        if(goodsName.equals(new String(goodsName.getBytes("iso8859-1"), "iso8859-1")))
        {
            str=new String(goodsName.getBytes("ISO-8859-1"),"UTF-8");
        }
        else {
            str = goodsName;
        }
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

    @RequestMapping("shop/{store_id}")
    public ModelAndView cateClick(@PathVariable("store_id")Long storeId,
                                  @RequestParam(value = "page", required = false, defaultValue = "1")int page,
                                  @RequestParam(value = "rows", required = false, defaultValue = "20")int rows){
        ModelAndView mav = new ModelAndView();
        List<Cate> cateList = cateService.getAllCates();
        List<Goods> goodsList = goodsService.getGoodsByStoreId(storeId, page, rows);
        mav.addObject("cateList", cateList);
        PageInfo<Goods> pageInfo =new  PageInfo<Goods>(goodsList);
        mav.addObject("goodsList",goodsList);
        mav.addObject("pageInfo", pageInfo);
        Store store = shopService.getStoreByStoreId(storeId);
        mav.addObject("store", store);
        User solder = userService.getUserById(store.getUser_id());
        mav.addObject("solder", solder);
        mav.setViewName("shop/shop_show/shop_show");
        return mav;
    }

    @RequestMapping("shop/{store_id}/{cate_id}")
    public ModelAndView cateClick(@PathVariable("store_id")Long storeId,
                                  @PathVariable("cate_id")Long cateId,
                                  @RequestParam(value = "page", required = false, defaultValue = "1")int page,
                                  @RequestParam(value = "rows", required = false, defaultValue = "20")int rows){
        ModelAndView mav = new ModelAndView();
        List<Cate> cateList = cateService.getAllCates();
        List<Goods> goodsList = goodsService.getGoodsByStoreIdAndCateId(storeId, cateId, page, rows);
        mav.addObject("cateList", cateList);
        PageInfo<Goods> pageInfo =new  PageInfo<Goods>(goodsList);
        mav.addObject("goodsList",goodsList);
        mav.addObject("pageInfo", pageInfo);
        mav.addObject("cate_id", cateId);
        Store store = shopService.getStoreByStoreId(storeId);
        mav.addObject("store", store);
        User solder = userService.getUserById(store.getUser_id());
        mav.addObject("solder", solder);
        mav.setViewName("shop/shop_show/shop_show");
        return mav;
    }

    @RequestMapping("/shop/{store_id}/search")
    public ModelAndView searchStoreGoods(@RequestParam("word")String goodsName,
                                    @PathVariable("store_id")Long storeId,
                                    @RequestParam(value = "page", required = false, defaultValue = "1")int page,
                                    @RequestParam(value = "rows", required = false, defaultValue = "20")int rows)
            throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        List<Cate> cateList = cateService.getAllCates();
        modelAndView.addObject("cateList", cateList);
        String str=new String(goodsName.getBytes("ISO-8859-1"),"UTF-8");
        List<Goods> goodsList = goodsService.searchGoodsByNameAndStoreId(str, storeId, page, rows);
        LoggingUtil.log(str);
        PageInfo<Goods> pageInfo =new  PageInfo<Goods>(goodsList);
        modelAndView.addObject("goodsList",goodsList);
        modelAndView.addObject("pageInfo", pageInfo);
        modelAndView.setViewName("shop/shop_show/search_result");
        modelAndView.addObject("word", str);
        modelAndView.addObject("page", page);
        Store store = shopService.getStoreByStoreId(storeId);
        modelAndView.addObject("store", store);
        User solder = userService.getUserById(store.getUser_id());
        modelAndView.addObject("solder", solder);
        return modelAndView;

    }

    @RequestMapping("/chat")
    public ModelAndView chat(@RequestParam("toId")String toId,
                             HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.getUserByLoginName(UserUtil.getUserName(request));
        modelAndView.addObject("clientId", user.getUsername());
        modelAndView.addObject("toId", toId);
        modelAndView.setViewName("chat/chat");
        return modelAndView;
    }

}
