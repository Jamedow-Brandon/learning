package com.jamedow.laodoufang.web;

import com.jamedow.laodoufang.entity.Product;
import com.jamedow.laodoufang.service.CategoryService;
import com.jamedow.laodoufang.service.ProductService;
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
@RequestMapping("/bg")
public class BGController {
    private static final Logger logger = LoggerFactory.getLogger(BGController.class);

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "product/list")
    public ModelAndView list(String channel) {
        ModelAndView view = new ModelAndView();
        view.addObject("channel", channel);
        view.setViewName("product/list");
        return view;
    }

    @RequestMapping(value = "product/detail")
    public ModelAndView detail(Integer productId) {
        ModelAndView view = new ModelAndView();
        view.setViewName("product/detail");

        Product product = productService.getProductById(productId);

        view.addObject("product", product);
        return view;
    }
}
