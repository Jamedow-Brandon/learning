package com.jamedow.laodoufang.web;

import com.jamedow.laodoufang.entity.Category;
import com.jamedow.laodoufang.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by 365 on 2016/12/9 0009.
 */
@Controller
@RequestMapping("snack")
public class SnackController {
    private static final Logger logger = LoggerFactory.getLogger(SnackController.class);
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView();
        view.setViewName("snack/snack");

        List<Category> categories = categoryService.getAllCategories();
        view.addObject("categories", categories);

        return view;
    }
}
