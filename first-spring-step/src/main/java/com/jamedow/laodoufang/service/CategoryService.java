package com.jamedow.laodoufang.service;

import com.jamedow.laodoufang.entity.Category;

import java.util.List;

/**
 * Created by 365 on 2016/12/12 0012.
 */

public interface CategoryService {


    List<Category> getCategoriesByParentId(Integer parentId);

    List<Category> getAllCategories();

    Category getCategoryById(Integer categoryId);
}
