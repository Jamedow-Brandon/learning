package com.jamedow.laodoufang.service.impl;

import com.jamedow.laodoufang.entity.Recipe;
import com.jamedow.laodoufang.service.ElasticSearchService;
import com.jamedow.laodoufang.service.RecipeService;
import com.jamedow.laodoufang.utils.es.EsClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;

/**
 * Description
 * <p>
 * Created by Administrator on 2017/8/2.
 */
@Service
public class ElasticSearchServiceImpl implements ElasticSearchService {
    private Logger logger = LoggerFactory.getLogger(EsClient.class);

    @Autowired
    private RecipeService recipeService;

    @Override
    public void search(String content, int page, int pageSize) {
        int from = (page - 1) * pageSize;
        List<String> keywords = EsClient.analyze(content);
        BoolQueryBuilder bool = QueryBuilders.boolQuery();
        for (String keyword : keywords) {
            bool.should(termQuery("name", keyword));
        }
        EsClient.search("laodoufang", "recipe", bool, from, pageSize);
    }

    public void insertRecipeToEs(Recipe recipe) throws IOException {
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

        EsClient.createDocument("laodoufang", "recipe", builder);
    }

    @Override
    public void initRecipes() {
        List<Recipe> recipes = recipeService.queryAll();
        for (Recipe recipe : recipes) {
            try {
                insertRecipeToEs(recipe);
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }
}
