package com.springcloud.dubbo_provider.framework.http.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HeartbeatHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(HeartbeatHandler.class);

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt == IdleStateEvent.READER_IDLE_STATE_EVENT) {
            ctx.close();
            logger.info("heart beat find reader idle event ,so close connection ");
        }
        if (evt == IdleStateEvent.WRITER_IDLE_STATE_EVENT) {
            ctx.close();
            logger.info("heart beat find writer idle event ,so close connection ");
        }
        if (evt == IdleStateEvent.ALL_IDLE_STATE_EVENT) {
            ctx.close();
            logger.info("heart beat find all idle event ,so close connection ");
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("心跳处理错误: {}:{}", ctx.channel(), cause);
        ctx.close();
    }
}

