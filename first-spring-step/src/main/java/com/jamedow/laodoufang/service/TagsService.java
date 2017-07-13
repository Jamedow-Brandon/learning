package com.jamedow.laodoufang.service;

import com.jamedow.laodoufang.entity.Tags;

import java.util.List;

/**
 * Created by 365 on 2016/12/12 0012.
 */

public interface TagsService {

    int saveTag(Tags tags);

    List<Tags> getTagsByParentId(Integer parentId);

    List<Tags> getBrothersByTagsId(Integer tagsId);
}
