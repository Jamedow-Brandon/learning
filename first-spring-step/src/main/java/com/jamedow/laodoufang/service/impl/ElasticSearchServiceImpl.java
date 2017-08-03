package com.jamedow.laodoufang.service.impl;

import com.jamedow.laodoufang.service.ElasticSearchService;
import com.jamedow.laodoufang.service.RecipeService;
import com.jamedow.laodoufang.utils.es.EsClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.termQuery;

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
    public void search(String content, int page, int pageSize) {
        int from = (page - 1) * pageSize;
        List<String> keywords = EsClient.analyze(content);
        BoolQueryBuilder bool = QueryBuilders.boolQuery();
        for (String keyword : keywords) {
            bool.should(termQuery("name", keyword));
        }
        EsClient.search("laodoufang", "recipe", bool, from, pageSize);
    }
}
