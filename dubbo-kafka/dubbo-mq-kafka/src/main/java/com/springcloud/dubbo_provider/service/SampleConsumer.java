package com.springcloud.dubbo_provider.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 类的功能描述：
 * 消息消费者者用于处理消息
 *
 * @ClassName: MessageConsumer
 * @Author
 * @Date
 */
@Service
public class SampleConsumer {
    private static Logger logger = LoggerFactory.getLogger(SampleConsumer.class);
    private Gson gson = new GsonBuilder().create();

    @KafkaListener(topics = "test",containerFactory="batchLogFactory")
    public void listen(ConsumerRecord<?, ?> consumerRecord, Acknowledgment ack) {
        Optional<?> kafkaMessage = Optional.ofNullable(consumerRecord.value());
        if (kafkaMessage.isPresent()) {
            // 处理消息
            try{
                String message = (String) kafkaMessage.get();
                System.out.println("record =" + consumerRecord);
                System.out.println("message =" + message);
                // 处理业务过程---注意重复消息的处理。
                System.out.println("处理业务过程: =" + message);
            } catch (Exception e){
                e.printStackTrace();
                // 将消息存储到error表中,需要手工处理这些数据
                String message = (String) kafkaMessage.get();
            } finally {
                // 业务处理完毕才能手工提交offset,如果处理过程中有异常发生，则记录一下，然后提交一下偏移量
                logger.info("偏移量:" + consumerRecord.offset());
                ack.acknowledge();
            }
        }
    }

}