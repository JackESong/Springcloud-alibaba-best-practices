package com.springcloud.dubbo_provider;

import io.rsocket.RSocket;
import io.rsocket.RSocketFactory;
import io.rsocket.frame.decoder.PayloadDecoder;
import io.rsocket.transport.netty.client.TcpClientTransport;
import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;
import org.springframework.util.MimeTypeUtils;

import java.net.InetSocketAddress;

@SpringBootApplication
public class CientApplication {

    public static void main(String[] args) {
        SpringApplication.run(CientApplication.class, args);
    }

    @Bean
    @SneakyThrows
    RSocket rSocket() {
        return RSocketFactory.connect()
                .dataMimeType(MimeTypeUtils.APPLICATION_JSON_VALUE)
                .frameDecoder(PayloadDecoder.ZERO_COPY)
                .transport(TcpClientTransport.create(new InetSocketAddress("127.0.0.1", 7000)))
                .start()
                .block();
    }

    @Bean
    RSocketRequester requester(RSocketStrategies strategies) {
        return RSocketRequester.wrap(rSocket(), MimeTypeUtils.APPLICATION_JSON,MimeTypeUtils.APPLICATION_JSON, strategies);
    }
}




