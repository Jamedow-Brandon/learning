package com.jamedow.laodoufang.service.impl;

import com.jamedow.laodoufang.entity.Recipe;
import com.jamedow.laodoufang.entity.RecipeExample;
import com.jamedow.laodoufang.entity.Users;
import com.jamedow.laodoufang.mapper.RecipeMapper;
import com.jamedow.laodoufang.service.RecipeService;
import com.jamedow.laodoufang.utils.es.EsClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * Created by ydy on 2017/2/15.
 */
@Service
@Transactional
public class RecipeServiceImpl implements RecipeService {
    private Logger logger = LoggerFactory.getLogger(RecipeServiceImpl.class);

    @Autowired
    private RecipeMapper recipeMapper;


    @Override
    public List<Recipe> getRecipesByCategoryId(Integer categoryId) {
        RecipeExample example = new RecipeExample();
        example.createCriteria().andCategoryEqualTo(categoryId);
        return recipeMapper.selectByExample(example);
    }

    @Override
    public Recipe getRecipeById(Integer recipeId) {
        return recipeMapper.selectByPrimaryKey(recipeId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int saveRecipe(Recipe recipe) throws Exception {
        if (null != recipe.getId()) {
            return recipeMapper.updateByPrimaryKeySelective(recipe);
        }
        int result = recipeMapper.insert(recipe);
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
        return result;
    }

    @Override
    public List<Recipe> queryAll() {
        return recipeMapper.selectByExample(new RecipeExample());
    }

    @Override
    public String saveRecipeAndRel(Users user, String name, String intro, String tags, String ingredient, String burdening) {
        Recipe recipe = new Recipe();
        recipe.setName(name);
        recipe.setIntro(intro);
        recipe.setBurdening(burdening);
        recipe.setIngredient(ingredient);
        recipe.setUserId(user.getId().intValue());
        recipe.setTags(tags);
        recipe.setIsOfficial("0");
        recipe.setCreateTime(Calendar.getInstance().getTime());
        int result = 0;
        try {
            result = saveRecipe(recipe);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*if((result !=0) && (null!=recipe.getId())){
            String[] tagsArray = tags.split(",");
            for(String tag : tagsArray){

                RecipeTagsRel RecipeTagsRel = new RecipeTagsRel();
                RecipeTagsRel.setRecipeId(Recipe.getId());
                RecipeTagsRel.setTagsId(Integer.parseInt(tag));
                result = RecipeTagsRelMapper.insert(RecipeTagsRel)*result;
            }
        }*/
        if(result != 0)
            return "保存成功";
        return "保存失败";

    }

}
