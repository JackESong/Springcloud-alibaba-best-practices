package com.springcloud.dubbo_provider;

import com.springcloud.dubbo_provider.framework.http.EchoServer;
import io.netty.channel.ChannelFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringNettyApplication {

    @Value("${netty.port}")
    private int port;

    @Value("${netty.url}")
    private String url;

    @Autowired
    private EchoServer echoServer;

    public static void main(String[] args) {
        SpringApplication.run(SpringNettyApplication.class, args);
    }

}


