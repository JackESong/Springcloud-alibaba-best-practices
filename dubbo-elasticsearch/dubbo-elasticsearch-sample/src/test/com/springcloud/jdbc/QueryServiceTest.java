package com.springcloud.jdbc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.springcloud.jdbc.po.Es;
import com.springcloud.jdbc.po.Order;
import com.springcloud.jdbc.service.QueryService;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = JdbcApplicaiton.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QueryServiceTest {

    @Autowired
    BulkProcessor bulkProcessor;

    @Autowired
    TransportClient transportClient;

    @Autowired
    QueryService queryService;

    /**
     *
     * 创建index 输入数据
     *
     * */
    @Before
    public void setUp(){
        Random random = new Random();
        for(int i = 0 ; i < 1200; i++){
            Order order = new Order();
            int j = random.nextInt(20) % 20 + 1;
            order.setId(i);
            order.setStoreId(j);
            order.setStoreName("旗舰店"+ j);
            order.setCategoryId(j);
            order.setCategoryCode("shirt_"+j);
            order.setProductCode("product_"+i);
            order.setQuantity(random.nextInt(20) % 20 + 1);
            order.setAmount(200 + (random.nextInt(20) % 20 + 1));
            order.setPayDate(new Date());
            String jsonStr = JSON.toJSONString(order, SerializerFeature.WriteDateUseDateFormat);
            bulkProcessor.add(new IndexRequest("search_index",
                    "search_index", i+"")
                    .source(jsonStr, XContentType.JSON));
        }

    }



    /**
     *
     * 复杂查询
     *
     * */

    @Test
    public void testQueryListFromES(){
        Es es = new Es("search_index","search_index");
        List<Map<String, Object>> list = queryService
                .queryListFromES(es, 13,"旗舰店"+13, "2018-12-01", "2018-12-31");
        System.out.println(JSON.toJSONString(list));
    }


}

