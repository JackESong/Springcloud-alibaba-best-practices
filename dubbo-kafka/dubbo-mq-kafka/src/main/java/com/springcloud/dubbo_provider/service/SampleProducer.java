package com.springcloud.dubbo_provider.service;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;

@Component
public class SampleProducer {

    private static Logger logger = LoggerFactory.getLogger(SampleProducer.class);

    @Autowired
    private KafkaTemplate kafkaTemplate;

    private Gson gson = new GsonBuilder().create();

    public void send(String topic,String msg) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, msg);
        future.addCallback(
                new SuccessCallback<SendResult<String, String>>() {
                    @Override
                    public void onSuccess(SendResult<String, String> stringStringSendResult) {
                        logger.info("KafkaMessageProducer 发送消息成功！");
//                        logger.info(stringStringSendResult.getProducerRecord().toString());//元数据
//                        logger.info(stringStringSendResult.getRecordMetadata().toString());//发送到kafka的位置信息
//                        logger.info(stringStringSendResult.toString());//发送到kafka的位置信息
                        // 发送成功需要单独记录下来，以便后期对账
                       handleSuccessResult(stringStringSendResult,msg,topic);
                    }
                },
                new FailureCallback() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println(throwable);
                       logger.info("KafkaMessageProducer 发送消息失败！" + throwable);
                        // 发送失败需要单独记录下来，后期单独同步
                        handleErrorResult(throwable,msg,topic);

                    }
                }
        );

    }

    private void handleErrorResult(Throwable throwable, String msg, String topic) {
    }

    private void handleSuccessResult(SendResult<String, String> stringStringSendResult,String msg ,String topic) {
    }

}