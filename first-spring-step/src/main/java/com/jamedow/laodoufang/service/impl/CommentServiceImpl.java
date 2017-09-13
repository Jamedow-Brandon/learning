package com.jamedow.laodoufang.service.impl;

import com.jamedow.laodoufang.common.system.bean.Page;
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
    public List<RecipeComment> getRecipeComments(Integer recipeId, Page page) {
        RecipeCommentExample example = new RecipeCommentExample();
        example.createCriteria().andRecipeIdEqualTo(recipeId);
        example.setOrderByClause("rc.is_picked desc,rc.vote_up desc,rc.create_time desc");
        example.setPaging(page);
        return recipeCommentMapper.getCommentsByRecipeId(example);
    }

    @Override
    public int deleteRecipeCommentById(Integer id) {
        return recipeCommentMapper.deleteByPrimaryKey(id);
    }
}
