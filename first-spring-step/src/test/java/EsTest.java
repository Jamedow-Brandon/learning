import com.jamedow.laodoufang.service.RecipeService;
import com.jamedow.laodoufang.utils.es.EsClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;

/**
 * Created by Administrator on 2017/4/11 0011.
 */
@ContextConfiguration(locations = {"classpath:/spring/spring-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class EsTest {
    static Logger logger = LoggerFactory.getLogger(EsTest.class);

    @Autowired
    private RecipeService recipeService;

    @Test
    public void testCreateType() {
        try {
            XContentBuilder builder = jsonBuilder()
                    .startObject()
                    .startObject("recipe")
                    .startObject("properties")
                    .startObject("id").field("type", "integer").endObject()
                    .startObject("name").field("type", "string").field("analyzer", "standard").field("search_analyzer", "standard").endObject()
                    .startObject("intro").field("type", "string").endObject()
                    .startObject("createTime").field("type", "date").endObject()
                    .startObject("linkUrl").field("type", "string").endObject()
                    .startObject("imgUrl").field("type", "string").endObject()
                    .startObject("tags").field("type", "string").field("analyzer", "standard").field("search_analyzer", "standard").endObject()
                    .startObject("voteUp").field("type", "integer").endObject()
                    .startObject("voteDown").field("type", "integer").endObject()
                    .startObject("isOfficial").field("type", "string").endObject()
                    .startObject("userId").field("type", "string").endObject()
                    .startObject("trafficVolume").field("type", "integer").endObject()
                    .startObject("ingredient").field("type", "string").field("analyzer", "standard").field("search_analyzer", "standard").endObject()
                    .startObject("burdening").field("type", "string").field("analyzer", "standard").field("search_analyzer", "standard").endObject()
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
        QueryBuilder qb1 = termQuery("name", "吃");
        QueryBuilder qb2 = termQuery("name", "排");
        QueryBuilder qb3 = termQuery("name", "骨");
        BoolQueryBuilder bool = QueryBuilders.boolQuery();
        bool.should(qb1);
        bool.should(qb2);
        bool.should(qb3);
        EsClient.search("laodoufang", "recipe", bool, 0, 10);
    }
}
