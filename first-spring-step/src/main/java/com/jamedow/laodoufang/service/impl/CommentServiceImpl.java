package com.jamedow.laodoufang.service.impl;

import com.jamedow.laodoufang.entity.RecipeComment;
import com.jamedow.laodoufang.entity.RecipeCommentExample;
import com.jamedow.laodoufang.mapper.RecipeCommentMapper;
import com.jamedow.laodoufang.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ydy on 2017/2/15.
 */
@Service
@Transactional
public class CommentServiceImpl implements CommentService {


    @Autowired
    private RecipeCommentMapper recipeCommentMapper;

    @Override
    public List<RecipeComment> getRecipeComments(Integer recipeId) {
        RecipeCommentExample example = new RecipeCommentExample();
        example.createCriteria().andRecipeIdEqualTo(recipeId);
        example.setOrderByClause("vote_up desc,create_time desc");
        return recipeCommentMapper.selectByExample(example);
    }

    @Override
    public int deleteRecipeCommentById(Integer id) {
        return recipeCommentMapper.deleteByPrimaryKey(id);
    }
}
