package com.hqlguigu.es.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;

/**
 * 类描述
 *
 * @author abel.huang@zkteco.com
 * @date 2022-07-05 13:42
 * @since 1.0.0
 */
public class Elasticsearch01_Client {

    public static void main(String[] args) {
        try {
            RestHighLevelClient client = getRestHighLevelClient();
            //创建索引
//            getIndex(client);
            // 查询索引 - 请求对象
//            searchIndex(client);
            // 删除索引 - 请求对象
//            delIndex(client);
            // 新增文档 - 请求对象
//            addIndex(client);
// 修改文档 - 请求对象
//            updateIndex(client);
            //查询文档
//            searchW(client);
//            delW(client);
//创建批量新增请求对象
//            bulkAdd(client);
            // 创建搜索请求对象
            SearchRequest request = new SearchRequest();
            request.indices("student");
// 构建查询的请求体 
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
// 查询所有数据
            sourceBuilder.query(QueryBuilders.matchAllQuery());
            request.source(sourceBuilder);
            SearchResponse response = client.search(request, RequestOptions.DEFAULT);
// 查询匹配
            SearchHits hits = response.getHits();
            System.out.println("took:" + response.getTook());
            System.out.println("timeout:" + response.isTimedOut());
            System.out.println("total:" + hits.getTotalHits());
            System.out.println("MaxScore:" + hits.getMaxScore());
            System.out.println("hits========>>");
            for (SearchHit hit : hits) {
//输出每条查询的结果信息
                System.out.println(hit.getSourceAsString());
            }
            System.out.println("<<========");

            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }

    private static void bulkAdd(RestHighLevelClient client) throws IOException {
        BulkRequest request = new BulkRequest();
        request.add(new
                IndexRequest().index("user").id("1001").source(XContentType.JSON, "name",
                "zhangsan"));
        request.add(new IndexRequest().index("user").id("1002").source(XContentType.JSON, "name",
                "lisi"));
        request.add(new
                IndexRequest().index("user").id("1003").source(XContentType.JSON, "name",
                "wangwu"));
//客户端发送请求，获取响应对象
        BulkResponse responses = client.bulk(request, RequestOptions.DEFAULT);
//打印结果信息
        System.out.println("took:" + responses.getTook());
        System.out.println("items:" + responses.getItems());
    }

    private static void delW(RestHighLevelClient client) throws IOException {
        //创建请求对象
        DeleteRequest request = new DeleteRequest().index("user").id("1001");
        //客户端发送请求，获取响应对象
        DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
        //打印信息
        System.out.println(response.toString());
    }

    private static void searchW(RestHighLevelClient client) throws IOException {
        //1.创建请求对象
        GetRequest request = new GetRequest().index("user").id("1001");
        //2.客户端发送请求，获取响应对象
        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        //3.打印结果信息
        System.out.println("_index:" + response.getIndex());
        System.out.println("_type:" + response.getType());
        System.out.println("_id:" + response.getId());
        System.out.println("source:" + response.getSourceAsString());
    }

    private static void updateIndex(RestHighLevelClient client) throws IOException {
        UpdateRequest request = new UpdateRequest();
// 配置修改参数
        request.index("user").id("1001");
// 设置请求体，对数据进行修改
        request.doc(XContentType.JSON, "sex", "女");
// 客户端发送请求，获取响应对象
        UpdateResponse response = client.update(request, RequestOptions.DEFAULT);
        System.out.println("_index:" + response.getIndex());
        System.out.println("_id:" + response.getId());
        System.out.println("_result:" + response.getResult());
    }

    private static void addIndex(RestHighLevelClient client) throws IOException {
        IndexRequest request = new IndexRequest();
        // 设置索引及唯一性标识
        request.index("user").id("1001");
        // 创建数据对象
        User user = new User();
        user.setName("zhangsan");
        user.setAge(30);
        user.setSex("男");
        ObjectMapper objectMapper = new ObjectMapper();
        String productJson = objectMapper.writeValueAsString(user);
// 添加文档数据，数据格式为 JSON 格式
        request.source(productJson, XContentType.JSON);
// 客户端发送请求，获取响应对象
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);
////3.打印结果信息
        System.out.println("_index:" + response.getIndex());
        System.out.println("_id:" + response.getId());
        System.out.println("_result:" + response.getResult());
    }

    static class User {
        private String name;
        private Integer age;
        private String sex;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }
    }


    private static void delIndex(RestHighLevelClient client) throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest("user");
        // 发送请求，获取响应
        AcknowledgedResponse response = client.indices().delete(request,
                RequestOptions.DEFAULT);
        // 操作结果
        System.out.println("操作结果 ： " + response.isAcknowledged());
    }

    private static void searchIndex(RestHighLevelClient client) throws IOException {
        GetIndexRequest request = new GetIndexRequest("user");
        // 发送请求，获取响应
        GetIndexResponse response = client.indices().get(request,
                RequestOptions.DEFAULT);
        System.out.println("aliases:" + response.getAliases());
        System.out.println("mappings:" + response.getMappings());
        System.out.println("settings:" + response.getSettings());
    }

    private static RestHighLevelClient getRestHighLevelClient() {
        return new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );
    }

    private static void getIndex(RestHighLevelClient client) throws IOException {
        // 创建索引 - 请求对象
        CreateIndexRequest request = new CreateIndexRequest("user");
        // 发送请求，获取响应
        CreateIndexResponse response = client.indices().create(request,
                RequestOptions.DEFAULT);
        boolean acknowledged = response.isAcknowledged();
        // 响应状态
        System.out.println("操作状态 = " + acknowledged);
    }


}
