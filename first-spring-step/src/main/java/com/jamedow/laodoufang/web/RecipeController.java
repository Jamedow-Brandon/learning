package com.jamedow.laodoufang.web;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by 365 on 2016/12/9 0009.
 */
@Controller
@RequestMapping("/recipe")
public class RecipeController {
    private static final Logger logger = LoggerFactory.getLogger(RecipeController.class);

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    @Autowired
    private TagsService tagsService;

    @RequestMapping(value = "list")
    public ModelAndView list() {
        ModelAndView view = new ModelAndView();
        view.setViewName("recipe/list");

        List<Tags> tags = tagsService.getTagsByParentId(0);

        view.addObject("tags", JSONArray.fromObject(tags));
        return view;
    }

    @RequestMapping(value = "add")
    public ModelAndView detail(Integer recipeId) {
        ModelAndView view = new ModelAndView();
        view.setViewName("recipe/edit");

        Product recipe = productService.getProductById(recipeId);

        view.addObject("recipe", recipe);

        List<Tags> tags = tagsService.getLeaves();

        view.addObject("tags", JSONArray.fromObject(tags));

        List<Tags> fodders = tagsService.getTagsByParentId(2);

        for (Tags fodder : fodders) {
            List<Tags> children = tagsService.getTagsByParentId(fodder.getId());
            fodder.setTags(children);
        }

        view.addObject("fodders", JSONArray.fromObject(fodders));
        return view;
    }

    @RequestMapping(value = "getChildrenTags", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String getChildrenTags(Integer tagsId) {
        List<Tags> childrenTags = tagsService.getTagsByParentId(tagsId);
        return JSONArray.fromObject(childrenTags).toString();
    }

    @RequestMapping(value = "getBrothersByTagsId", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String getBrothersByTagsId(Integer tagsId) {
        List<Tags> brothersTags = tagsService.getBrothersByTagsId(tagsId);
        return JSONArray.fromObject(brothersTags).toString();
    }
}
