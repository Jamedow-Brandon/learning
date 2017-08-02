package com.jamedow.laodoufang.utils.elasticsearch;

import org.elasticsearch.common.xcontent.XContentBuilder;

/**
 * Description
 * <p>
 * Created by Administrator on 2017/8/1.
 */
public interface EsClientInterface<T> {
    XContentBuilder convertBean2Builder(T t);
}
