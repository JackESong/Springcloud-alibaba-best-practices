package com.springcloud.dubbo_provider.framework.http.handler;


import static io.netty.handler.codec.http.HttpHeaders.Names.CONNECTION;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

import com.alibaba.fastjson.JSON;
import com.springcloud.dubbo_provider.framework.e.BasicConstants;
import com.springcloud.dubbo_provider.framework.entity.Response;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders.Names;
import io.netty.handler.codec.http.HttpHeaders.Values;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpProtocolEncoder extends ChannelOutboundHandlerAdapter {

    private FullHttpResponse response;
    private static Logger LOGGER = LoggerFactory
            .getLogger(HttpProtocolEncoder.class);

    @Override
    public void write(ChannelHandlerContext ctx, Object msg,
                      ChannelPromise promise) throws Exception {
        boolean keepAlive = false;
        if (msg instanceof Response) {
            Response res = (Response) msg;
            keepAlive = isKeepAlive(res.getDatas());
            res.addLogs("connection type keepAlive " + keepAlive);
            if (res.getContent() != null) {
                response = getHttpResponse(res.getContent());
            } else {
                response = getHttpResponse("response content is null "
                        .getBytes());
            }
            // response.headers().set(Names.CONTENT_ENCODING, "utf-8");
            res.addLogs("response end...");
            Map<String, Object> dataMap = res.getDatas();
            for (String key : dataMap.keySet()) {
                try {
                    response.headers().set(key, dataMap.get(key));
                } catch (Exception e) {
                    LOGGER.error(e + "");
                }
            }
            //日志输出
            writeLogs(res.getLogs());
        } else {
            response = getHttpResponse(msg.toString().getBytes());
        }

        if (!keepAlive) {
            ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);

        } else {
            response.headers().set(CONNECTION, Values.KEEP_ALIVE);
            ctx.write(response);
        }
    }

    private boolean isKeepAlive(Map<String, Object> message) {
        Object connection = message.get("Connection");
        if (connection != null && !"close".equalsIgnoreCase(connection.toString())){
            return true;
        } else {
            return false;
        }
    }

    private FullHttpResponse getHttpResponse(byte[] rep) {
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK,
                Unpooled.wrappedBuffer(rep));
        response.headers().set(
                Names.CONTENT_TYPE,
                "text/plain; charset="
                        + BasicConstants.HTTP_SERVER_URL_CHARCODE_UTF_8);
        response.headers().set(Names.CONTENT_LENGTH,
                new Integer(response.content().readableBytes()));
        response.headers().set(Names.CONNECTION, Values.CLOSE);
        return response;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        LOGGER.error("error:", cause);
        ctx.close();
    }

    private void writeLogs(List<String> logs) {
        LOGGER.info(JSON.toJSONString(logs));
    }

}

