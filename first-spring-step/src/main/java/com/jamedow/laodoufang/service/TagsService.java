package com.jamedow.laodoufang.service;

import com.jamedow.laodoufang.entity.Tags;

import java.util.List;

/**
 * Created by 365 on 2016/12/12 0012.
 */

public interface TagsService {

    int saveTag( Tags tags);

    /**
     * 查找分类
     * @return
     */
    List<Tags> queryClassify();

    /**
     * 根据父节点查询子节点
     * @param classifyId
     * @return
     */
    List<Tags> queryTagsByClassify(int classifyId);

    /**
     * 删除标签
     * @param tagsId
     * @return
     */
    String deleteTags(int tagsId);
}
