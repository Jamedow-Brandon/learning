package com.jamedow.laodoufang.mapper;

import com.jamedow.laodoufang.entity.RecipeComment;
import com.jamedow.laodoufang.entity.RecipeCommentExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeCommentMapper {
    long countByExample(RecipeCommentExample example);

    int deleteByExample(RecipeCommentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RecipeComment record);

    int insertSelective(RecipeComment record);

    List<RecipeComment> selectByExample(RecipeCommentExample example);

    List<RecipeComment> getCommentsByRecipeId(RecipeCommentExample example);

    RecipeComment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RecipeComment record, @Param("example") RecipeCommentExample example);

    int updateByExample(@Param("record") RecipeComment record, @Param("example") RecipeCommentExample example);

    int updateByPrimaryKeySelective(RecipeComment record);

    int updateByPrimaryKey(RecipeComment record);
}