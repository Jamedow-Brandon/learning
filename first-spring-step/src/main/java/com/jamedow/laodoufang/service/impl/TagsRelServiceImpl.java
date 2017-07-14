package com.jamedow.laodoufang.service.impl;

import com.jamedow.laodoufang.entity.TagsRel;
import com.jamedow.laodoufang.entity.TagsRelExample;
import com.jamedow.laodoufang.mapper.TagsRelMapper;
import com.jamedow.laodoufang.service.TagsRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by yoyo on 2017/7/13.
 */
@Service
@Transactional
public class TagsRelServiceImpl implements TagsRelService {

    @Autowired
    private TagsRelMapper tagsRelMapper;

    @Override
    public List<TagsRel> queryTagsRel(TagsRel tagsRel) {

        TagsRelExample example = new TagsRelExample();
        TagsRelExample.Criteria criteria = example.createCriteria();

        if(tagsRel!=null){

            if(null != tagsRel.getParentId()){
                criteria.andParentIdEqualTo(tagsRel.getParentId());
            }
            if(null != tagsRel.getTagId()){
                criteria.andTagIdEqualTo(tagsRel.getTagId());
            }

        }
        return tagsRelMapper.selectByExample(example);
    }

    @Override
    public int deleteTagsRel(int tagsRelId) {

        return tagsRelMapper.deleteByPrimaryKey(tagsRelId);
    }

    @Override
    public int save(TagsRel tagsRel) {

        if(null!=tagsRel.getId())
            return tagsRelMapper.updateByPrimaryKeySelective(tagsRel);
        return tagsRelMapper.insert(tagsRel);



    }
}
