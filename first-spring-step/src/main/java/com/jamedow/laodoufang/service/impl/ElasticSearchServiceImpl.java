package com.jamedow.laodoufang.service.impl;

import com.jamedow.laodoufang.common.system.bean.Page;
import com.jamedow.laodoufang.entity.Recipe;
import com.jamedow.laodoufang.plugin.es.EsClient;
import com.jamedow.laodoufang.service.ElasticSearchService;
import com.jamedow.laodoufang.service.RecipeService;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public SearchHit[] search(String content, String[] tags, String isOfficial, Page page) {
        int from = page.getCurrentPage() * page.getPageSize();
        List<String> keywords = EsClient.analyze(content);
        BoolQueryBuilder bool = QueryBuilders.boolQuery();
        for (String keyword : keywords) {
            bool.should(termQuery("name", keyword));
        }
        if (tags != null) {
            for (String tag : tags) {
                bool.should(termQuery("tags", tag));
            }
        }
        if (isOfficial != null) {
            bool.should(termQuery("isOfficial", isOfficial));
        }
        SearchResponse searchResponse = EsClient.search("laodoufang", "recipe", bool, from, page.getPageSize());
        page.setRecords((int) searchResponse.getHits().getTotalHits());

        return searchResponse.getHits().getHits();
    }

    public String insertRecipeToEs(Recipe recipe) {
        String searchDocumentId = null;
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

            searchDocumentId = EsClient.createDocument("laodoufang", "recipe", recipe.getSearchDocumentId(), builder);
        } catch (Exception e) {
            logger.error("新建食谱文档失败{}", recipe.getId(), e.getMessage(), e);
        }
        return searchDocumentId;
    }

    @Override
    public void initRecipes() {
        List<Recipe> recipes = recipeService.queryAll();
        for (Recipe recipe : recipes) {
            String searchDocumentId = insertRecipeToEs(recipe);
            if (!searchDocumentId.equals(recipe.getSearchDocumentId())) {
                recipe.setSearchDocumentId(searchDocumentId);
                recipeService.saveRecipe(recipe);
            }
        }
    }
}
