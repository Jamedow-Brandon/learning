package com.jamedow.laodoufang.mapper;

import com.jamedow.laodoufang.entity.ProductTagsRel;
import com.jamedow.laodoufang.entity.ProductTagsRelExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductTagsRelMapper {
    long countByExample(ProductTagsRelExample example);

    int deleteByExample(ProductTagsRelExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductTagsRel record);

    int insertSelective(ProductTagsRel record);

    List<ProductTagsRel> selectByExample(ProductTagsRelExample example);

    ProductTagsRel selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductTagsRel record, @Param("example") ProductTagsRelExample example);

    int updateByExample(@Param("record") ProductTagsRel record, @Param("example") ProductTagsRelExample example);

    int updateByPrimaryKeySelective(ProductTagsRel record);

    int updateByPrimaryKey(ProductTagsRel record);
}