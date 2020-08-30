package com.springcloud.dubbo_provider.framework.http;

import io.netty.channel.ChannelFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class NettybootServerInitConfig implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private EchoServer echoServer;

    @Value("${netty.port}")
    private int port;

    @Value("${netty.url}")
    private String url;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event)  {

        if(event.getApplicationContext().getParent() == null){
            ChannelFuture future = null;
            try {
                future = echoServer.start(url,port);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Runtime.getRuntime().addShutdownHook(new Thread(){
                @Override
                public void run() {
                    echoServer.destroy();
                }
            });
            //服务端管道关闭的监听器并同步阻塞,直到channel关闭,线程才会往下执行,结束进程
            if (future != null){
                future.channel().closeFuture().syncUninterruptibly();
            }
        }
    }

}
