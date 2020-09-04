package com.springcloud.dubbo_provider.framework.http.handler;

import com.springcloud.dubbo_provider.framework.e.BasicConstants;
import com.springcloud.dubbo_provider.framework.entity.Request;
import com.springcloud.dubbo_provider.framework.entity.ResultMsg;
import com.springcloud.dubbo_provider.framework.http.tools.ByteBufToBytes;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpRequest;

import java.net.InetSocketAddress;


public class HttpProtocolDecoder extends ChannelInboundHandlerAdapter {

    private Request request	= null;
    private ByteBufToBytes reader;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Object obj = msg;
        boolean isContinue = true;

        if (msg instanceof HttpRequest) {
            HttpRequest req = (HttpRequest) msg;

            HttpHeaders headers = req.headers();
            request = new Request(req);
            request.addLogs("request begin...");
            String clientIP = req.headers().get("X-Forwarded-For");
            if (clientIP == null) {
                InetSocketAddress insocket = (InetSocketAddress) ctx.channel()
                        .remoteAddress();
                clientIP = insocket.getAddress().getHostAddress();
            }
            request.setClientIP(clientIP);
            request.addLogs(clientIP);
            request.setRequestMethod(req.getMethod().toString());
            for (String key : headers.names()) {
                request.putHead(key, headers.get(key));
            }

            if (HttpHeaders.isContentLengthSet(req)) {
                reader = new ByteBufToBytes((int) HttpHeaders.getContentLength(req));
                isContinue = false;
            }


        }

        if (msg instanceof HttpContent) {
            HttpContent httpContent = (HttpContent) msg;
            ByteBuf content = httpContent.content();
            if(null != reader){
                reader.reading(content);
                content.release();
                isContinue = false;
                if (reader.isEnd()) {
                    byte[] data = reader.readFull();
                    request.setContent(data);
                    isContinue = true;
                }
            }else{
                return;
            }
        }

        if (isContinue) {
            obj = request;
            ctx.fireChannelRead(obj);
        }
    }


    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
//		LOGGER.error("error:",cause);
        ResultMsg res = new ResultMsg();
        res.addReturnDesc(BasicConstants.EXCEPTION_DES);
        res.addReturnCode(BasicConstants.RETURN_CODE_ERROR);
        ctx.fireChannelRead(res.toJson());
    }


}

