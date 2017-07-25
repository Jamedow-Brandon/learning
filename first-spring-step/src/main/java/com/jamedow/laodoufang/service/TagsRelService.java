package com.jamedow.laodoufang.service;

import com.jamedow.laodoufang.entity.TagsRel;

import java.util.List;

/**
 * Created by yoyo on 2017/7/13.
 */
public interface TagsRelService {

    /**
     * 根据条件查找TagsRel
     * @param tagsRel
     * @return
     */
    public List<TagsRel> queryTagsRel(TagsRel tagsRel);

    /**
     * 根据id删除TagsRel
     * @param tagsRelId
     * @return
     */
    public int deleteTagsRel(int tagsRelId);

    /**
     * 添加或修改tagsRel
     * @param tagsRel
     * @return
     */
    int save(TagsRel tagsRel);

    public int deleteRelByTagId(int tagsId);
}
