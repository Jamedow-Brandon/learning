package com.jamedow.laodoufang.service.impl;

import com.jamedow.laodoufang.entity.Tags;
import com.jamedow.laodoufang.mapper.TagsMapper;
import com.jamedow.laodoufang.mapper.TagsMapperEx;
import com.jamedow.laodoufang.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ydy on 2017/2/15.
 */
@Service
@Transactional
public class TagsServiceImpl implements TagsService {


    @Autowired
    private TagsMapper tagsMapper;

    @Autowired
    private TagsMapperEx tagsMapperEx;

    @Autowired
    private TagsService tagsService;

    @Override
    public int saveTag(Tags tags) {
        if (null != tags.getId()) {
            return tagsMapper.updateByPrimaryKeySelective(tags);
        }
        return tagsMapper.insert(tags);
    }

    @Override
    public List<Tags> queryClassify() {

        List<Tags> classifyList = tagsMapperEx.queryClassify();
        return  classifyList;
    }

    @Override
    public List<Tags> queryTagsByClassify(int classifyId) {

        List<Tags> tagsList = tagsMapperEx.queryTagByClassify(classifyId);
        return tagsList;
    }

    @Override
    public String deleteTags(int tagsId){

        List<Tags> tagsList = tagsService.queryTagsByClassify(tagsId);
        //没有子节点才可以删除
        if(tagsList.size() == 0){

            int result = tagsMapper.deleteByPrimaryKey(tagsId);
            if(result == 0 )
                return "删除失败";
            return "删除成功";
        }
        return "存在子节点,删除失败";
    }

}
