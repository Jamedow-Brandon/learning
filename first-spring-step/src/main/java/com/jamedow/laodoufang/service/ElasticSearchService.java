package com.jamedow.laodoufang.service;

import com.jamedow.laodoufang.entity.Recipe;

import java.io.IOException;

/**
 * Description
 * <p>
 * Created by Administrator on 2017/8/2.
 */
public interface ElasticSearchService {


    void search(String content, int page, int pageSize);

    void insertRecipeToEs(Recipe recipe) throws IOException;

    void initRecipes();
}
