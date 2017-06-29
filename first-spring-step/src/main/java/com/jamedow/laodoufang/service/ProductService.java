package com.jamedow.laodoufang.service;

import com.jamedow.laodoufang.entity.Product;

import java.util.List;

/**
 * Created by 365 on 2016/12/12 0012.
 */

public interface ProductService {

    List<Product> getProductsByCategoryId(Integer categoryId);
}
