package com.jamedow.laodoufang.service;

import com.jamedow.laodoufang.entity.Tags;
import com.jamedow.laodoufang.entity.ex.TagsExt;

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

    List<Tags> getTagsByParentId(Integer parentId);

    /**
     * 添加或修改classify
     * @param tags
     * @return
     */
    Tags saveClassify(Tags tags);

    /**
     * 根据条件查找Tags
     * @param tags
     * @return
     */
    List<Tags> queryTags(Tags tags);


    List<Tags> getBrothersByTagsId(Integer tagsId);


    /**
     * 查找分类以及所包含的子标签
     * @return
     */
    List<TagsExt> queryClassifyAndChildren();

    /**
     * 根据子标签找出所有父标签
     * @return
     */
    List<Tags> getParentsByTags(int tagsId);

    List<Tags> getLeaves();
}
