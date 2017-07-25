package com.jamedow.laodoufang.entity.ex;

import com.jamedow.laodoufang.entity.Tags;

import java.util.List;

/**
 * Created by yoyo on 2017/7/18.
 */
public class TagsExt extends Tags {

    List<Tags> childrenTagsList ;//子标签

    List<Tags> parentTagsList ;//父标签

    public List<Tags> getChildrenTagsList() {
        return childrenTagsList;
    }

    public void setChildrenTagsList(List<Tags> childrenTagsList) {
        this.childrenTagsList = childrenTagsList;
    }

    public List<Tags> getParentTagsList() {
        return parentTagsList;
    }

    public void setParentTagsList(List<Tags> parentTagsList) {
        this.parentTagsList = parentTagsList;
    }
}
