package com.jamedow.laodoufang.web;

import com.jamedow.laodoufang.entity.Category;
import com.jamedow.laodoufang.entity.Product;
import com.jamedow.laodoufang.entity.Tags;
import com.jamedow.laodoufang.service.CategoryService;
import com.jamedow.laodoufang.service.ProductService;
import com.jamedow.laodoufang.service.TagsService;
import net.sf.json.JSONArray;
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
@RequestMapping("/product")
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    @Autowired
    private TagsService tagsService;

    @RequestMapping(value = "introList")
    public ModelAndView introList(Integer categoryId) {
        ModelAndView view = new ModelAndView();
        view.setViewName("product/introList");

        Category category = categoryService.getCategoryById(categoryId);

        List<Product> products = productService.getProductsByCategoryId(categoryId);

        view.addObject("products", products);
        view.addObject("category", category);
        return view;
    }

    @RequestMapping(value = "list")
    public ModelAndView list() {
        ModelAndView view = new ModelAndView();
        view.setViewName("product/list");

        List<Tags> tags = tagsService.getTagsByParentId(0);

        view.addObject("tags", JSONArray.fromObject(tags));
        return view;
    }

    @RequestMapping(value = "detail")
    public ModelAndView detail(Integer productId) {
        ModelAndView view = new ModelAndView();
        view.setViewName("product/detail");

        Product product = productService.getProductById(productId);

        view.addObject("product", product);
        return view;
    }
}
