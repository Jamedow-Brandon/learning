package com.jamedow.laodoufang.service.impl;

import com.jamedow.laodoufang.entity.Product;
import com.jamedow.laodoufang.entity.ProductExample;
import com.jamedow.laodoufang.mapper.ProductMapper;
import com.jamedow.laodoufang.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ydy on 2017/2/15.
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {


    @Autowired
    private ProductMapper productMapper;


    @Override
    public List<Product> getProductsByCategoryId(Integer categoryId) {
        ProductExample example = new ProductExample();
        example.createCriteria().andCategoryEqualTo(categoryId);
        return productMapper.selectByExample(example);
    }
}
