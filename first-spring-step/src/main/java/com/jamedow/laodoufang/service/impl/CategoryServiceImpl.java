package com.jamedow.laodoufang.service.impl;

import com.jamedow.laodoufang.entity.Category;
import com.jamedow.laodoufang.entity.CategoryExample;
import com.jamedow.laodoufang.mapper.CategoryMapper;
import com.jamedow.laodoufang.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ydy on 2017/2/15.
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> getCategoriesByParentId(Integer parentId) {

        CategoryExample example = new CategoryExample();
        if (null != parentId) {
            example.createCriteria().andParentIdEqualTo(parentId);
        } else {
            example.createCriteria().andParentIdIsNull();
        }
        return categoryMapper.selectByExample(example);
    }

    @Override
    public List<Category> getAllCategories() {

        List<Category> categories = this.getCategoriesByParentId(null);
        for (Category category : categories) {
            getChildrenCategories(category);
        }

        return categories;
    }

    private void getChildrenCategories(Category parentCategory) {
        List<Category> childrenCategories = getCategoriesByParentId(parentCategory.getId());
        parentCategory.setChildrenCategories(childrenCategories);
        for (Category category : childrenCategories) {
            getChildrenCategories(category);
        }
    }
}
