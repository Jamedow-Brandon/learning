package com.jamedow.laodoufang.service;

import com.jamedow.laodoufang.entity.Recipe;

import java.util.List;

/**
 * Created by 365 on 2016/12/12 0012.
 */

public interface RecipeService {

    List<Recipe> getRecipesByCategoryId(Integer categoryId);

    Recipe getRecipeById(Integer recipeId);

    /**
     * 保存食谱以及食谱和标签关联
     *
     * @return
     */
    int saveRecipe(Recipe recipe);

    List<Recipe> queryAll();
}
