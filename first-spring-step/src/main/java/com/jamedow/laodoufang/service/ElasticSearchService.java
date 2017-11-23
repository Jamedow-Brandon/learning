package com.jamedow.laodoufang.service;

import com.jamedow.laodoufang.common.system.bean.Page;
import com.jamedow.laodoufang.entity.Recipe;
import org.elasticsearch.search.SearchHit;

/**
 * Description
 * <p>
 * Created by Administrator on 2017/8/2.
 */
public interface ElasticSearchService {


    SearchHit[] search(String content, String[] tags, String isOfficial, Page page);

    String insertRecipeToEs(Recipe recipe);

    void initRecipes();
}
