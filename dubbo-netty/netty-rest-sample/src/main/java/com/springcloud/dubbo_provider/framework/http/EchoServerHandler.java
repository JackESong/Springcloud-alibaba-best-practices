package com.springcloud.dubbo_provider.framework.http;

import com.springcloud.dubbo_provider.framework.http.action.BaseController;
import com.springcloud.dubbo_provider.project.route.DispathActionManager;
import com.springcloud.dubbo_provider.framework.http.auth.TokenManager;
import com.springcloud.dubbo_provider.framework.e.BasicConstants;
import com.springcloud.dubbo_provider.framework.entity.Request;
import com.springcloud.dubbo_provider.framework.entity.Response;
import com.springcloud.dubbo_provider.framework.entity.ResultMsg;
import static io.netty.handler.codec.http.HttpHeaders.Names.CONNECTION;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * 服务端自定义业务处理handler
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    private static Logger LOGGER = LoggerFactory
            .getLogger(EchoServerHandler.class);

    private static TokenManager tokenManager = null;

    private Request request;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {

        if (msg instanceof Request) {
            request = (Request) msg;
            System.out.println(request);
            request.addLogs(request.toString());
            String jsonResult = "";
            ResultMsg res = new ResultMsg();
            try {
                String requestPath = request.getHttpPath();
                BaseController action = null;
//                String token = request.get("jwt_token");
                action = DispathActionManager.getAction(request);
                if ("GET".equals(request.getRequestMethod())|| "POST".equals(request.getRequestMethod())) {
                    jsonResult = action.doAction(request);
                    request.addLogs(request.getRequestMethod() + "- json result is " + jsonResult);
                }  else {
                    request.addLogs("do nothing...");
                    return;
                }

            }catch (Exception e) {
                LOGGER.error("error message is :", e);
                res.addReturnCode(BasicConstants.RETURN_CODE_ERROR);
                res.addReturnDesc(BasicConstants.EXCEPTION_DES);
                jsonResult = res.toJson();
            }finally{
                Response response = new Response();
                response.setContent(jsonResult.getBytes());
                response.setLogs(request.getLogs());
                response.put(CONNECTION, request.getHead(CONNECTION));
                ctx.write(response);
            }
        }else{
            ctx.write(msg);
        }

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//		super.channelReadComplete(ctx);
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        LOGGER.error("error:",cause);
        ResultMsg res = new ResultMsg();
        res.addReturnDesc(BasicConstants.EXCEPTION_DES);
        res.addReturnCode(BasicConstants.RETURN_CODE_ERROR);
        ctx.write(res.toJson());
    }
}
