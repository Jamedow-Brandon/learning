package com.jamedow.laodoufang.service.impl;

import com.jamedow.laodoufang.entity.Recipe;
import com.jamedow.laodoufang.entity.RecipeExample;
import com.jamedow.laodoufang.mapper.RecipeMapper;
import com.jamedow.laodoufang.service.ElasticSearchService;
import com.jamedow.laodoufang.service.RecipeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by ydy on 2017/2/15.
 */
@Service
@Transactional
public class RecipeServiceImpl implements RecipeService {
    private Logger logger = LoggerFactory.getLogger(RecipeServiceImpl.class);

    @Autowired
    private ElasticSearchService esService;
    @Autowired
    private RecipeMapper recipeMapper;


    @Override
    public List<Recipe> getRecipesByCategoryId(Integer categoryId) {
        RecipeExample example = new RecipeExample();
        example.createCriteria().andCategoryEqualTo(categoryId);
        return recipeMapper.selectByExample(example);
    }

    @Override
    public Recipe getRecipeById(Integer recipeId) {
        return recipeMapper.selectByPrimaryKey(recipeId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int saveRecipe(Recipe recipe) {
        if (null != recipe.getId()) {
            return recipeMapper.updateByPrimaryKeySelective(recipe);
        }
        recipe.setIsOfficial("0");
        recipe.setCreateTime(new Date());
        int result = recipeMapper.insert(recipe);
        esService.insertRecipeToEs(recipe);
        return result;
    }

    @Override
    public List<Recipe> queryAll() {
        return recipeMapper.selectByExample(new RecipeExample());
    }
}
