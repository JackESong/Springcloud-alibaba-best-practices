package com.springcloud.dubbo_provider.framework.http.handler;

import com.springcloud.dubbo_provider.framework.http.EchoServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.timeout.IdleStateHandler;



public class HttpServerChannelInitializer extends ChannelInitializer<SocketChannel>
{
    @Override
    protected void initChannel(SocketChannel ch) throws Exception
    {
        ChannelPipeline pipeline = ch.pipeline();
        // server端接收到的是httpRequest，所以要使用HttpRequestDecoder进行解码
        pipeline.addLast(new HttpRequestDecoder());
        pipeline.addLast(new HttpProtocolDecoder());
        // server端发送的是httpResponse，所以要使用HttpResponseEncoder进行编码
        pipeline.addLast(new HttpResponseEncoder());
        pipeline.addLast(new HttpRequestTimeOut(180));
        pipeline.addLast(new HttpProtocolEncoder());
        // 增加心跳检测事件
        pipeline.addLast("idle",new IdleStateHandler(10, 0, 0));
        pipeline.addLast("idleClose", new HeartbeatHandler());

        pipeline.addLast(new EchoServerHandler());

    }

}
