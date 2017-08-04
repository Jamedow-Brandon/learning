import com.jamedow.laodoufang.entity.Recipe;
import com.jamedow.laodoufang.service.RecipeService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * Created by Administrator on 2017/4/11 0011.
 */
@ContextConfiguration(locations = {"classpath:/spring/spring-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class RecipeTest {
    static Logger logger = LoggerFactory.getLogger(RecipeTest.class);

    @Autowired
    private RecipeService recipeService;

    @Test
    public void testMain() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setName("砂锅土豆粉");
        recipe.setIntro("糖醋排骨很好吃，酸酸甜甜");
        recipe.setDetail("糖醋排骨做法:1.2.3.4");
        recipe.setCreateTime(new Date());
        recipe.setLinkUrl("");
        recipe.setImgUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502459540&di=2b3fcce54cdeb2501e518df373c9b76e&imgtype=jpg&er=1&src=http%3A%2F%2Fimg-cdn.hopetrip.com.hk%2Fnews3%2FBFCBDE849E081BAE%2F9%2F9261D386A8BB9ED0CE4E.jpg");
        recipe.setCategory(1);

        JSONArray tags = new JSONArray();
        JSONObject tag1 = new JSONObject();
        tag1.put("id", 10);
        tag1.put("name", "香辣");
        tags.add(tag1);

        JSONObject tag2 = new JSONObject();
        tag2.put("id", 35);
        tag2.put("name", "烧");
        tags.add(tag2);
        recipe.setTags(tags.toString());
        recipe.setVoteUp(245);
        recipe.setVoteDown(2);
        recipe.setIsOfficial("0");
        recipe.setUserId(1);
        recipe.setTrafficVolume(1289);

        JSONArray ingredients = new JSONArray();
        JSONObject ingredient1 = new JSONObject();
        ingredient1.put("id", 113);
        ingredient1.put("name", "大白菜");
        ingredient1.put("volume", "一棵");
        ingredients.add(ingredient1);

        JSONObject ingredient2 = new JSONObject();
        ingredient2.put("id", 118);
        ingredient2.put("name", "芒果");
        ingredient2.put("volume", "200克");
        ingredients.add(ingredient2);
        recipe.setIngredient(ingredients.toString());

        JSONArray burdenings = new JSONArray();
        JSONObject burdening1 = new JSONObject();
        burdening1.put("id", 119);
        burdening1.put("name", "葡萄");
        burdening1.put("volume", "二勺");
        burdenings.add(burdening1);

        JSONObject burdening2 = new JSONObject();
        burdening1.put("id", 116);
        burdening1.put("name", "石榴");
        burdening1.put("volume", "适量");
        burdenings.add(burdening2);
        recipe.setBurdening(burdenings.toString());

        recipeService.saveRecipe(recipe);
    }
}
