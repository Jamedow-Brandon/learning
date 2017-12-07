package com.jamedow.laodoufang.mapper;

import com.jamedow.laodoufang.entity.VoteLog;
import com.jamedow.laodoufang.entity.VoteLogExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteLogMapper {
    long countByExample(VoteLogExample example);

    int deleteByExample(VoteLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(VoteLog record);

    int insertSelective(VoteLog record);

    List<VoteLog> selectByExample(VoteLogExample example);

    VoteLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") VoteLog record, @Param("example") VoteLogExample example);

    int updateByExample(@Param("record") VoteLog record, @Param("example") VoteLogExample example);

    int updateByPrimaryKeySelective(VoteLog record);

    int updateByPrimaryKey(VoteLog record);
}