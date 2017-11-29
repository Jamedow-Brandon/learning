package com.jamedow.laodoufang.web;

import com.jamedow.laodoufang.common.system.bean.GridReturn;
import com.jamedow.laodoufang.common.system.bean.Page;
import com.jamedow.laodoufang.common.system.bean.ReturnResult;
import com.jamedow.laodoufang.entity.Recipe;
import com.jamedow.laodoufang.entity.Tags;
import com.jamedow.laodoufang.entity.Users;
import com.jamedow.laodoufang.service.ElasticSearchService;
import com.jamedow.laodoufang.service.RecipeService;
import com.jamedow.laodoufang.service.TagsService;
import com.jamedow.laodoufang.service.VoteService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.search.SearchHit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by 365 on 2016/12/9 0009.
 */
@Controller
@RequestMapping("/recipe")
public class RecipeController {
    private static final Logger logger = LoggerFactory.getLogger(RecipeController.class);

    @Autowired
    private ElasticSearchService esService;
    @Autowired
    private RecipeService recipeService;
    @Autowired
    private TagsService tagsService;
    @Autowired
    private VoteService voteService;


    @RequestMapping(value = "list")
    public ModelAndView list(HttpServletRequest request) {
        ModelAndView view = new ModelAndView();
        view.setViewName("recipe/list");

        String searchKeyWord = request.getParameter("searchKeyWord");
        Integer currentPage = Integer.valueOf(request.getParameter("currentPage") == null ? "1" : request.getParameter("currentPage"));
        Page page = new Page();
        page.setCurrentPage(currentPage);
        if (StringUtils.isNotBlank(searchKeyWord)) {
            SearchHit[] hits = esService.search(searchKeyWord, null, null, page);
            view.addObject("hits", hits);
        }
        view.addObject("searchKeyWord", searchKeyWord);

        List<Tags> tags = tagsService.getTagsByParentId(0);
        view.addObject("tags", JSONArray.fromObject(tags));
        view.addObject("page", page);
        return view;
    }

    @RequestMapping(value = "detail")
    public ModelAndView detail(HttpServletRequest request, Integer recipeId) {
        ModelAndView view = new ModelAndView();
        view.setViewName("recipe/detail");

        Recipe recipe = recipeService.getRecipeById(recipeId);
        view.addObject("recipe", recipe);
        return view;
    }

    @RequestMapping(value = "add")
    public ModelAndView detail(HttpSession session, Integer recipeId) {
        ModelAndView view = new ModelAndView();
        view.setViewName("recipe/add");
        Users users = (Users) session.getAttribute("user");
        if (users == null) {
            view.setViewName("redirect:/user/login");
        }


        Recipe recipe = recipeService.getRecipeById(recipeId);

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

    @RequestMapping("/saveMenu")
    @ResponseBody
    public Object saveMenu(HttpSession session, Recipe recipe) {
        Users user = (Users) session.getAttribute("user");
        if (null != user) {
            recipe.setUserId(user.getId());
            return recipeService.saveRecipe(recipe);
        }
        return 0;
    }

    @RequestMapping(value = "getRecipes", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object getRecipes(String searchKeyWord, String choseTags, String isOfficial, Integer currentPage) {
        Page page = new Page();
        page.setCurrentPage(currentPage);
        SearchHit[] hits = null;
        if (StringUtils.isNotBlank(searchKeyWord)) {
            hits = esService.search(searchKeyWord, choseTags, isOfficial, page);
        }
        JSONObject result = new JSONObject();
        result.put("hits", hits);
        result.put("page", page);
        return JSONObject.fromObject(result).toString();
    }

    @RequestMapping(value = "vote", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object vote(Integer status, Integer objId, HttpSession session) throws Exception {
        GridReturn gridReturn = new GridReturn();
        Users users = (Users) session.getAttribute("user");
        if (null == users) {

            gridReturn.setReturnResult(ReturnResult.UN_LOGIN);
            return gridReturn;
        }
        if (null == status || null == objId) {
            gridReturn.setReturnResult(ReturnResult.ABSENCE_PARAMETER);
            return gridReturn;
        }
        long voteCount = voteService.vote(status, objId, users.getId());
        gridReturn.setReturnResult(ReturnResult.SUCCESS);
        gridReturn.setTotalCount(voteCount);
        return gridReturn;
    }
}
