package com.jamedow.laodoufang.service.impl;

import com.jamedow.laodoufang.entity.Recipe;
import com.jamedow.laodoufang.entity.RecipeExample;
import com.jamedow.laodoufang.mapper.RecipeMapper;
import com.jamedow.laodoufang.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ydy on 2017/2/15.
 */
@Service
@Transactional
public class RecipeServiceImpl implements RecipeService {


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

    @Override
    public int saveRecipe(Recipe recipe) {
        return recipeMapper.insert(recipe);
    }
}
