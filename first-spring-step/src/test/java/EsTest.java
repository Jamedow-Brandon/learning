import com.jamedow.laodoufang.plugin.es.EsClient;
import com.jamedow.laodoufang.service.ElasticSearchService;
import com.jamedow.laodoufang.service.RecipeService;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.List;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * Created by Administrator on 2017/4/11 0011.
 */
@ContextConfiguration(locations = {"classpath:/spring/spring-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class EsTest {
    static Logger logger = LoggerFactory.getLogger(EsTest.class);

    @Autowired
    private RecipeService recipeService;
    @Autowired
    private ElasticSearchService elasticSearchService;

    @Test
    public void testCreateIndex() {
        EsClient.deleteIndex("test");
        EsClient.createIndex("laodoufang");
    }

    @Test
    public void testInitRecipes() {
        elasticSearchService.initRecipes();
    }

    @Test
    public void testCreateType() {
        try {
            XContentBuilder builder = jsonBuilder()
                    .startObject()
                    .startObject("recipe")
                    .startObject("properties")
                    .startObject("id").field("type", "integer").endObject()
                    .startObject("name").field("type", "string").field("analyzer", "ik_max_word").field("search_analyzer", "ik_max_word").endObject()
                    .startObject("intro").field("type", "string").endObject()
                    .startObject("createTime").field("type", "date").endObject()
                    .startObject("linkUrl").field("type", "string").endObject()
                    .startObject("imgUrl").field("type", "string").endObject()
                    .startObject("tags").field("type", "string").field("analyzer", "ik_max_word").field("search_analyzer", "ik_max_word").endObject()
                    .startObject("voteUp").field("type", "integer").endObject()
                    .startObject("voteDown").field("type", "integer").endObject()
                    .startObject("isOfficial").field("type", "string").endObject()
                    .startObject("userId").field("type", "string").endObject()
                    .startObject("trafficVolume").field("type", "integer").endObject()
                    .startObject("ingredient").field("type", "string").field("analyzer", "ik_max_word").field("search_analyzer", "ik_max_word").endObject()
                    .startObject("burdening").field("type", "string").field("analyzer", "ik_max_word").field("search_analyzer", "ik_max_word").endObject()
                    .endObject()
                    .endObject()
                    .endObject();

            EsClient.addType("laodoufang", "recipe", builder);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Test
    public void testSearch() throws Exception {
        List<String> keywords = EsClient.analyze("四川的东坡肉");
//        BoolQueryBuilder bool = QueryBuilders.boolQuery();
//        for (String keyword : keywords) {
//            bool.should(termQuery("name", keyword));
//        }
//        EsClient.search("laodoufang", "recipe", bool, 0, 10);
    }
}
