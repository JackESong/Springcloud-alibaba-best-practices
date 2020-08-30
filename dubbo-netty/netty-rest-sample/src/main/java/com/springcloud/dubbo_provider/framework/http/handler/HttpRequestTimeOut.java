package com.springcloud.dubbo_provider.framework.http.handler;


import com.springcloud.dubbo_provider.framework.e.BasicConstants;
import com.springcloud.dubbo_provider.framework.entity.ResultMsg;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpHeaders.Values;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.HttpHeaders.Names;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;


public class HttpRequestTimeOut extends ReadTimeoutHandler {
    private boolean				isClosed;
    private final static int	TIMEOUTSECONDS	= 5;

    public HttpRequestTimeOut() {
        this(TIMEOUTSECONDS);
    }

    public HttpRequestTimeOut(int timeoutSeconds) {
        super(timeoutSeconds, TimeUnit.SECONDS);
    }

    @Override
    protected void readTimedOut(ChannelHandlerContext ctx) throws Exception {
        if (!isClosed) {
            ResultMsg result = new ResultMsg();
            result.addReturnDesc("Connection timed out, please try again later.");
            result.addReturnCode(BasicConstants.RETURN_CODE_ERROR);
            ByteBuf bf = Unpooled.copiedBuffer(result.toJson(), CharsetUtil.UTF_8);
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, bf);
            response.headers().set(Names.CONTENT_TYPE, "text/plain; charset=UTF-8");
            response.headers().set(HttpHeaders.Names.CONTENT_LENGTH, bf.readableBytes());
            response.headers().set(Names.CONNECTION, Values.CLOSE);
            isClosed = true;
            ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }

}

