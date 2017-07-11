package com.jamedow.laodoufang.service.impl;

import com.jamedow.laodoufang.entity.Tags;
import com.jamedow.laodoufang.mapper.TagsMapper;
import com.jamedow.laodoufang.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ydy on 2017/2/15.
 */
@Service
@Transactional
public class TagsServiceImpl implements TagsService {


    @Autowired
    private TagsMapper tagsMapper;

    @Override
    public int saveTag(Tags tags) {
        if (null != tags.getId()) {
            return tagsMapper.updateByPrimaryKeySelective(tags);
        }
        return tagsMapper.insert(tags);
    }
}
