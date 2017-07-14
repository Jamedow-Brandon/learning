package com.jamedow.laodoufang.mapper;

import com.jamedow.laodoufang.entity.Tags;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by yoyo on 2017/7/12.
 */
@Repository
public interface TagsMapperEx {

    public List<Tags> queryClassify();

    public List<Tags> queryTagByClassify( int classifyId);

}
