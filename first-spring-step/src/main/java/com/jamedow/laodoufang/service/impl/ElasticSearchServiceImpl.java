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
                    .field("id", recipe.getId())
                    .field("name", recipe.getName())
                    .field("intro", recipe.getIntro())
                    .field("createTime", recipe.getCreateTime())
                    .field("linkUrl", recipe.getLinkUrl())
                    .field("imgUrl", recipe.getImgUrl())
                    .field("tags", recipe.getTags())
                    .field("voteUp", recipe.getVoteUp())
                    .field("voteDown", recipe.getVoteDown())
                    .field("isOfficial", recipe.getIsOfficial())
                    .field("userId", recipe.getUserId())
                    .field("trafficVolume", recipe.getTrafficVolume())
                    .field("ingredient", recipe.getIngredient())
                    .field("burdening", recipe.getBurdening())
                    .endObject();

            EsClient.addType("recipe_index", "recipe", builder);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }

    }
}
