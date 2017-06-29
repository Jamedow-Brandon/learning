package com.jamedow.laodoufang.web;

import com.jamedow.laodoufang.service.UsersService;
import com.jamedow.laodoufang.utils.redis.RedisPoolManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by 365 on 2016/12/9 0009.
 */
@Controller
@RequestMapping("/product")
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private UsersService usersService;
    @Autowired
    private RedisPoolManager redis;

    @RequestMapping(value = "introList")
    public ModelAndView introList(String channel) {
        ModelAndView view = new ModelAndView();
        view.addObject("channel", channel);
        view.setViewName("product/introList");
        return view;
    }

    @RequestMapping(value = "list")
    public ModelAndView list(String channel) {
        ModelAndView view = new ModelAndView();
        view.addObject("channel", channel);
        view.setViewName("product/list");
        return view;
    }
}
