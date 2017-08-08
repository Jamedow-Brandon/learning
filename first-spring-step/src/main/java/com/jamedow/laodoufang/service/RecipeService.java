package com.jamedow.laodoufang.service;

import com.jamedow.laodoufang.entity.Recipe;
import com.jamedow.laodoufang.entity.Users;

import java.util.List;

/**
 * Created by 365 on 2016/12/12 0012.
 */

public interface RecipeService {

    List<Recipe> getRecipesByCategoryId(Integer categoryId);

    Recipe getRecipeById(Integer recipeId);

    int saveRecipe(Recipe recipe) throws Exception;

    List<Recipe> queryAll();

    /**
     * 保存食谱以及食谱和标签关联
     * @return
     */
    String saveRecipeAndRel(Users user, String name, String intro, String tags, String ingredient, String burdening);
}
