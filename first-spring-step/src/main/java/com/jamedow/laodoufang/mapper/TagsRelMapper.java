package com.jamedow.laodoufang.mapper;

import com.jamedow.laodoufang.entity.TagsRel;
import com.jamedow.laodoufang.entity.TagsRelExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagsRelMapper {
    long countByExample(TagsRelExample example);

    int deleteByExample(TagsRelExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TagsRel record);

    int insertSelective(TagsRel record);

    List<TagsRel> selectByExample(TagsRelExample example);

    TagsRel selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TagsRel record, @Param("example") TagsRelExample example);

    int updateByExample(@Param("record") TagsRel record, @Param("example") TagsRelExample example);

    int updateByPrimaryKeySelective(TagsRel record);

    int updateByPrimaryKey(TagsRel record);
}