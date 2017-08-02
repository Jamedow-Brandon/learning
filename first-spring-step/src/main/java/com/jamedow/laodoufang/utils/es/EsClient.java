package com.jamedow.laodoufang.utils.es;

import com.jamedow.laodoufang.utils.StringUtils;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.Requests;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * Description
 * <p>
 * Created by Administrator on 2017/8/1.
 */
public class EsClient {
    private static final String hosts = "106.14.210.31:9200";     //节点IP
    private static final String host = "106.14.210.31";     //节点IP
    private static final String clusterName = "es";     //集群名称
    private static Logger logger = LoggerFactory.getLogger(EsClient.class);
    private static Client client;

    static {
        // 通过setting对象指定集群配置信息, 配置的集群名
        Settings settings = Settings.builder().put("cluster.name", clusterName) // 设置集群名
//                .put("client.transport.sniff", true) // 开启嗅探 , 开启后会一直连接不上, 原因未知
                .put("client.transport.ignore_cluster_name", true) // 忽略集群名字验证, 打开后集群名字不对也能连接上
                .build();
        try {
            // 集群地址配置
            List<InetSocketTransportAddress> list = new ArrayList<>();
            if (StringUtils.isNotEmpty(hosts)) {
                String[] strArr = hosts.split(",");
                for (String str : strArr) {
                    String[] addressAndPort = str.split(":");
                    String address = addressAndPort[0];
                    int port = Integer.valueOf(addressAndPort[1]);

                    InetSocketTransportAddress inetSocketTransportAddress = new InetSocketTransportAddress(
                            InetAddress.getByName(address), port);
                    list.add(inetSocketTransportAddress);
                }
            }
            // 这里可以同时连接集群的服务器,可以多个,并且连接服务是可访问的
            InetSocketTransportAddress addressList[] = list
                    .toArray(new InetSocketTransportAddress[list.size()]);

//            client = new PreBuiltTransportClient(settings)
//                    .addTransportAddresses(addressList);

            client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), 9300));

        } catch (UnknownHostException e) {
            logger.info("unknownHost={}", hosts, e);
        }
    }

    /**
     * 创建索引
     *
     * @param indexName 索引名称
     * @return
     */
    public static void createIndex(String indexName) {
        // 创建索引库

        if (isIndexExists("indexName")) {
            logger.debug("Index {} already exits!", indexName);
        } else {
            CreateIndexResponse cIndexResponse = client.admin().indices().prepareCreate(indexName).execute().actionGet();
            if (cIndexResponse.isAcknowledged()) {
                logger.debug("create index {} successfully！", indexName);
            } else {
                logger.debug("Fail to create index {}!", indexName);
            }

        }
    }

    /**
     * 根据索引名称删除索引
     *
     * @param indexName 索引名称
     */
    public static void deleteIndex(String indexName) {
        if (!isIndexExists(indexName)) {
            logger.debug(indexName + " not exists");
        } else {
            DeleteIndexResponse dResponse = client.admin().indices().prepareDelete(indexName)
                    .execute().actionGet();
            if (dResponse.isAcknowledged()) {
                logger.debug("delete index {} successfully!", indexName);
            } else {
                logger.debug("Fail to delete index {}", indexName);
            }
        }
    }

    /**
     * 判断索引是否存在
     *
     * @param indexName 索引名称
     * @return
     */
    public static boolean isIndexExists(String indexName) {
        IndicesExistsRequest isExistsRequest = new IndicesExistsRequest(indexName);

        IndicesExistsResponse isExistsResponse = client.admin().indices()
                .exists(isExistsRequest).actionGet();
        return isExistsResponse.isExists();
    }


    /**
     * 创建类型
     *
     * @param indexName 索引
     * @param type      类型
     * @param builder   类型对象
     */
    public static void addType(String indexName, String type, XContentBuilder builder) {
        PutMappingRequest mappingRequest = Requests.putMappingRequest(indexName).type(type).source(builder);
        client.admin().indices().putMapping(mappingRequest).actionGet();
    }

    /**
     * 创建文档
     *
     * @param indexName 索引
     * @param type      类型
     * @param builder   文档对象
     */
    public static IndexResponse createDocument(String indexName, String type, XContentBuilder builder) {
        return client.prepareIndex(indexName, type).setSource(builder).execute().actionGet();
    }

    /**
     * 删除文档
     *
     * @param indexName 索引
     * @param type      类型
     * @param id        文档id
     */
    public static DeleteResponse deleteDocumentById(String indexName, String type, String id) {
        return client.prepareDelete(indexName, type, id).execute().actionGet();
    }

    /**
     * 更新文档
     *
     * @param indexName 索引
     * @param type      类型
     * @param builder   文档对象
     */
    public static void updateDocument(String indexName, String type, String id, XContentBuilder builder) {
        UpdateRequest updateRequest = new UpdateRequest(indexName, type, id);
        updateRequest.doc(builder);
        try {
            client.update(updateRequest).get();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }


    /**
     * 查询数据
     *
     * @param index        索引
     * @param type         类型
     * @param queryBuilder 查询条件
     * @return
     */
    public static SearchResponse search(String index, String type, QueryBuilder queryBuilder) {
        SearchResponse response = client.prepareSearch(index).setTypes(type).setQuery(queryBuilder).execute().actionGet();
        SearchHits hits = response.getHits();
        if (hits.getTotalHits() > 0) {
            for (SearchHit hit : hits) {
                logger.debug("score:" + hit.getScore() + ":\t" + hit.getSource());// .get("title")
            }
        } else {
            logger.debug("搜到0条结果");
        }
        return response;
    }

    public static void main(String[] args) {
//        try {
//        // 定义索引字段属性,其实这里就是组合json,可以参考curl 方式创建索引的json格式  此处blog 和执行时blog必须一致
//            XContentBuilder builder = jsonBuilder()
//                    .startObject()
//                    .startObject("blog")
//                    .startObject("properties")
//                    .startObject("id").field("type", "integer").field("store", "yes").endObject()
//                    .startObject("title").field("type", "string").field("store", "yes").endObject()
//                    .startObject("content").field("type", "string").field("store", "yes").endObject()
//                    .endObject()
//                    .endObject()
//                    .endObject();
//        // 创建数据json 注意此ID是一个字段不是上面查询的id
//        XContentBuilder builder = jsonBuilder()
//                .startObject()
//                .field("id", "2")
//                .field("title", "我是标题")
//                .field("content", "我是内容")
//                .endObject();
//            EsClient.createIndex("test");    //创建索引  相当于关系型数据库的  数据库
//            EsClient.addType("test","blog");                // 创建类型，相当于关系型数据库的 表
//            EsClient.createDocument("test","blog");             //插入数据，相当于关系数据库的 记录
//            //e.g.1: 查询title字段中包含hibernate关键字的文档:
//
//            QueryBuilder qb1 = termQuery("title", "hibernate");
//            //e.g.2: 查询title字段或content字段中包含Git关键字的文档:
//
//            QueryBuilder qb2= QueryBuilders.multiMatchQuery("git", "title","content");
//        SearchResponse search = EsClient.search("test", "blog", "2");    //获取数据
//            logger.debug("==={}===", builder.string());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        try {
            XContentBuilder builder = jsonBuilder()
                    .startObject()
                    .startObject("id").field("type", "integer").field("store", "yes").endObject()
                    .startObject("name").field("analyze", "string").field("store", "yes").endObject()
                    .startObject("intro").field("type", "string").field("store", "yes").endObject()
                    .startObject("createTime").field("type", new Date()).field("store", "yes").endObject()
                    .startObject("linkUrl").field("type", "string").field("store", "yes").endObject()
                    .startObject("imgUrl").field("type", "string").field("store", "yes").endObject()
                    .startObject("tags").field("analyze", "string").field("store", "yes").endObject()
                    .startObject("voteUp").field("type", "integer").field("store", "yes").endObject()
                    .startObject("voteDown").field("type", "integer").field("store", "yes").endObject()
                    .startObject("isOfficial").field("type", "string").field("store", "yes").endObject()
                    .startObject("userId").field("type", "string").field("store", "yes").endObject()
                    .startObject("trafficVolume").field("type", "integer").field("store", "yes").endObject()
                    .startObject("ingredient").field("analyze", "string").field("store", "yes").endObject()
                    .startObject("burdening").field("analyze", "string").field("store", "yes").endObject()
                    .endObject();

            EsClient.addType("recipe_index", "recipe", builder);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
