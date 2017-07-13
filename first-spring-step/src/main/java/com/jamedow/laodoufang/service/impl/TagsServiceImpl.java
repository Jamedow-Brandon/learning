package com.jamedow.laodoufang.service.impl;

import com.jamedow.laodoufang.entity.Tags;
import com.jamedow.laodoufang.entity.TagsExample;
import com.jamedow.laodoufang.entity.TagsRel;
import com.jamedow.laodoufang.entity.TagsRelExample;
import com.jamedow.laodoufang.mapper.TagsMapper;
import com.jamedow.laodoufang.mapper.TagsRelMapper;
import com.jamedow.laodoufang.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    private TagsRelMapper tagsRelMapper;

    @Override
    public int saveTag(Tags tags) {
        if (null != tags.getId()) {
            return tagsMapper.updateByPrimaryKeySelective(tags);
        }
        return tagsMapper.insert(tags);
    }

    @Override
    public List<Tags> getTagsByParentId(Integer parentId) {

        TagsRelExample tagsRelExample = new TagsRelExample();
        tagsRelExample.createCriteria().andParentIdEqualTo(parentId);
        List<TagsRel> tagsRels = tagsRelMapper.selectByExample(tagsRelExample);

        List<Integer> tagsIds = new ArrayList<>();
        for (TagsRel tagsRel : tagsRels) {
            tagsIds.add(tagsRel.getTagId());
        }

        TagsExample tagsExample = new TagsExample();
        tagsExample.createCriteria().andIdIn(tagsIds);
        return tagsMapper.selectByExample(tagsExample);
    }

    @Override
    public List<Tags> getBrothersByTagsId(Integer tagsId) {
        List<Tags> brothersTags = new ArrayList<>();
        TagsRelExample tagsRelExample = new TagsRelExample();
        tagsRelExample.createCriteria().andTagIdEqualTo(tagsId);
        List<TagsRel> tagsRels = tagsRelMapper.selectByExample(tagsRelExample);

        if (tagsRels != null && tagsRels.size() != 0) {
            tagsRelExample.clear();
            tagsRelExample.createCriteria().andParentIdEqualTo(tagsRels.get(0).getParentId());
            List<TagsRel> brothersTagsRels = tagsRelMapper.selectByExample(tagsRelExample);

            List<Integer> tagsIds = new ArrayList<>();
            for (TagsRel tagsRel : brothersTagsRels) {
                tagsIds.add(tagsRel.getTagId());
            }

            TagsExample tagsExample = new TagsExample();
            tagsExample.createCriteria().andIdIn(tagsIds);
            brothersTags = tagsMapper.selectByExample(tagsExample);
        }
        return brothersTags;
    }
}
