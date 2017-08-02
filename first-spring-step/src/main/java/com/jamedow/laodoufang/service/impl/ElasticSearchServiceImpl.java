package com.jamedow.laodoufang.service.impl;

import com.jamedow.laodoufang.entity.Recipe;
import com.jamedow.laodoufang.service.ElasticSearchService;
import com.jamedow.laodoufang.service.RecipeService;
import com.jamedow.laodoufang.utils.es.EsClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * Description
 * <p>
 * Created by Administrator on 2017/8/2.
 */
public class ElasticSearchServiceImpl implements ElasticSearchService {
    private Logger logger = LoggerFactory.getLogger(EsClient.class);

    @Autowired
    private RecipeService recipeService;

    @Override
    public void insertRecipe(Integer recipeId) {
        Recipe recipe = recipeService.getRecipeById(recipeId);


        try {
            XContentBuilder builder = jsonBuilder()
                    .startObject()
                    .startObject("id").field("type", "integer").field("store", "yes").endObject()
                    .startObject("name").field("analyze", "string").field("store", "yes").endObject()
                    .startObject("intro").field("type", "string").field("store", "yes").endObject()
                    .startObject("createTime").field("type", "string").field("store", "yes").endObject()
                    .startObject("linkUrl").field("type", "string").field("store", "yes").endObject()
                    .startObject("imgUrl").field("type", "string").field("store", "yes").endObject()
                    .startObject("tags").field("analyze", "string").field("store", "yes").endObject()
                    .startObject("voteUp").field("type", "integer").field("store", "yes").endObject()
                    .startObject("voteDown").field("type", "integer").field("store", "yes").endObject()
                    .startObject("isOfficial").field("type", "string").field("store", "yes").endObject()
                    .startObject("userId").field("type", "string").field("store", "yes").endObject()
                    .startObject("trafficVolume").field("type", "integer").field("store", "yes").endObject()
                    .startObject("ingredient").field("analyze", "string").field("store", "yes").endObject()
                    .startObject("burdening").field("analyze", "string").field("store", "yes").endObject()
                    .endObject();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }

    }
}
