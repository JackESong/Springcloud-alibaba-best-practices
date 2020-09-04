package com.springcloud.dubbo_provider.framework.http.route;

import com.springcloud.dubbo_provider.framework.e.BasicConstants;
import com.springcloud.dubbo_provider.framework.e.BasicException;
import com.springcloud.dubbo_provider.framework.entity.ResultMsg;
import com.springcloud.dubbo_provider.framework.http.tools.NullWritable;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 请求分排器
 */
@Component
public class RequestDispatcher implements ApplicationContextAware {

    private ExecutorService executorService = Executors.newFixedThreadPool(500);
    private ApplicationContext app;

    /**
     * 发送
     *
     * @param ctx
     * @param invokeMeta
     */
    public void dispatcher(final ChannelHandlerContext ctx, final MethodInvokeMeta invokeMeta) {
        executorService.submit(() -> {
            ChannelFuture f = null;
            try {
                Class<?> interfaceClass = invokeMeta.getInterfaceClass();
                String name = invokeMeta.getMethodName();
                Object[] args = invokeMeta.getArgs();
                Class<?>[] parameterTypes = invokeMeta.getParameterTypes();
                Object targetObject = app.getBean(interfaceClass);
                Method method = targetObject.getClass().getMethod(name, parameterTypes);
                Object obj = method.invoke(targetObject, args);
                if (obj == null) {
                    f = ctx.writeAndFlush(NullWritable.nullWritable());
                } else {
                    f = ctx.writeAndFlush(obj);
                }
                f.addListener(ChannelFutureListener.CLOSE);
            } catch (Exception e) {
                ResultMsg res = new ResultMsg();
                res.addReturnDesc(BasicConstants.EXCEPTION_DES);
                String error = res.toJson();
                f = ctx.writeAndFlush(error);
            } finally {
                f.addListener(ChannelFutureListener.CLOSE);
            }
        });
    }

    /**
     * 加载当前application.xml
     *
     * @param ctx
     * @throws
     */
    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BasicException {
        this.app = ctx;
    }
}
