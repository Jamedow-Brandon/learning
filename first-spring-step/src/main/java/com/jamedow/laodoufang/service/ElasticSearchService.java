package com.jamedow.laodoufang.service;

/**
 * Description
 * <p>
 * Created by Administrator on 2017/8/2.
 */
public interface ElasticSearchService {


    void search(String content, int page, int pageSize);
}
