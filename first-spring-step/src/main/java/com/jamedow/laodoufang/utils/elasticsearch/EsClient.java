package com.jamedow.laodoufang.utils.elasticsearch;

import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.ClusterAdminClient;
import org.elasticsearch.client.Requests;
import org.elasticsearch.cluster.health.ClusterHealthStatus;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * Description
 * <p>
 * Created by Administrator on 2017/8/1.
 */
public class EsClient {
    private static final String host = "106.14.210.31";     //节点IP
    private static final String clusterName = "elasticsearch";     //集群名称
    static Client client;
    private static Logger logger = LoggerFactory.getLogger(EsClient.class);

    static {
        // 通过setting对象指定集群配置信息, 配置的集群名
        Settings settings = Settings.builder().put("cluster.name", clusterName) // 设置集群名
//                .put("client.transport.sniff", true) // 开启嗅探 , 开启后会一直连接不上, 原因未知
                .put("client.transport.ignore_cluster_name", true) // 忽略集群名字验证, 打开后集群名字不对也能连接上
                .build();
        try {
            client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), 9300));
        } catch (UnknownHostException e) {
            logger.info("unknownHost={}", host, e);
        }
    }

    /**
     * 通过ID查询数据，
     * test为索引库，blog为类型，id为标识
     */

    public static SearchResponse search(String id) {
        return client.prepareSearch("test").setTypes().setQuery(QueryBuilders.termQuery("id", id)).execute().actionGet();
    }

    /**
     * 创建索引，indexName 索引名称
     */
    public static CreateIndexResponse createIndex(String indexName) throws IOException {
        return client.admin().indices().prepareCreate(indexName).execute().actionGet();
    }

    //集群状态
    public static void clusterStatus() {
        //注意集群的client获取方式略有不同，
        ClusterAdminClient clusterAdminClient = client.admin().cluster();
        ClusterHealthResponse healths = clusterAdminClient.prepareHealth().get();
        String clusterName = healths.getClusterName();
        int numberOfDataNodes = healths.getNumberOfDataNodes();
        int numberOfNodes = healths.getNumberOfNodes();
        ClusterHealthStatus status = healths.getStatus();
        System.out.println(clusterName + "###" + numberOfDataNodes + "###" + status.name());

    }

    public static void addType() throws IOException {
        // 定义索引字段属性,其实这里就是组合json,可以参考curl 方式创建索引的json格式  此处blog 和执行时blog必须一致
        XContentBuilder builder = jsonBuilder()
                .startObject()
                .startObject("blog")
                .startObject("properties")
                .startObject("id").field("type", "integer").field("store", "yes").endObject()
                .startObject("title").field("type", "string").field("store", "yes").endObject()
                .startObject("content").field("type", "string").field("store", "yes").endObject()
                .endObject()
                .endObject()
                .endObject();

        PutMappingRequest mappingRequest = Requests.putMappingRequest("test").type("blog").source(builder);
        client.admin().indices().putMapping(mappingRequest).actionGet();

    }

    /**
     * 创建数据
     */
    public static void createData() throws IOException {
        // 创建数据json 注意此ID是一个字段不是上面查询的id
        XContentBuilder builder = jsonBuilder()
                .startObject()
                .field("id", "2")
                .field("title", "我是标题")
                .field("content", "我是内容")
                .endObject();
        IndexResponse indexResponse = client.prepareIndex("test", "blog").setSource(builder).execute().actionGet();
        System.out.println(indexResponse.status());
    }

    public static void main(String[] args) {
//        try {
//            EsClient.createIndex("test");    //创建索引  相当于关系型数据库的  数据库
//            EsClient.addType();                // 创建类型，相当于关系型数据库的 表
//            EsClient.createData();             //插入数据，相当于关系数据库的 记录
        SearchResponse search = EsClient.search("2");    //获取数据
        System.out.println("======");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
