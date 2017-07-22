package com.shop.controller.buyer;

import org.apache.commons.net.ftp.parser.MacOsPeterFTPEntryParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by 18240 on 2017/7/20.
 */
@Controller
public class buyerController {

    @RequestMapping("buyer_home_page.do")
    public ModelAndView buyer_home_page(){
        return new ModelAndView("buyer/buyer_home_page");
    }
}
