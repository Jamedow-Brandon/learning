package com.jamedow.laodoufang.mapper;

import com.jamedow.laodoufang.entity.BaseAttachment;
import com.jamedow.laodoufang.entity.BaseAttachmentExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaseAttachmentMapper {
    long countByExample(BaseAttachmentExample example);

    int deleteByExample(BaseAttachmentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BaseAttachment record);

    int insertSelective(BaseAttachment record);

    List<BaseAttachment> selectByExample(BaseAttachmentExample example);

    BaseAttachment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BaseAttachment record, @Param("example") BaseAttachmentExample example);

    int updateByExample(@Param("record") BaseAttachment record, @Param("example") BaseAttachmentExample example);

    int updateByPrimaryKeySelective(BaseAttachment record);

    int updateByPrimaryKey(BaseAttachment record);
}