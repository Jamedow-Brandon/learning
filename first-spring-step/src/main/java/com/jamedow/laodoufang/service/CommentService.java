package com.jamedow.laodoufang.service;

import com.jamedow.laodoufang.entity.RecipeComment;

import java.util.List;

/**
 * Created by 365 on 2016/12/12 0012.
 */

public interface CommentService {
    List<RecipeComment> getRecipeComments(Integer recipeId);

    int deleteRecipeCommentById(Integer id);
}
