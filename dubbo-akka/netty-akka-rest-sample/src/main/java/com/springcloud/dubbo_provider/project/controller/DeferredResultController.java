package com.springcloud.dubbo_provider.project.controller;

import com.springcloud.dubbo_provider.framework.e.BasicConstants;
import com.springcloud.dubbo_provider.framework.entity.Request;
import com.springcloud.dubbo_provider.framework.entity.ResultMsg;
import com.springcloud.dubbo_provider.project.model.Message;
import com.springcloud.dubbo_provider.project.service.CompletableFutureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class DeferredResultController {

    private static final Long DEFERRED_RESULT_TIMEOUT = 1000L;

    private final AtomicLong id = new AtomicLong(0);

    @Autowired
    private CompletableFutureService completableFutureService;

    private ResultMsg resultObject = new ResultMsg();

    @RequestMapping("/async-non-blocking")
    @ResponseBody
    public Object getAsyncNonBlocking(Request request) {
        DeferredResult<Message> deferredResult = new DeferredResult<>(DEFERRED_RESULT_TIMEOUT);
        CompletableFuture<Message> completableFuture = completableFutureService.get("async-non-blocking", id.getAndIncrement());
        completableFuture.whenComplete((result, error) -> {
            if (error != null) {
                deferredResult.setErrorResult(error);
            } else {
                deferredResult.setResult(result);
                System.out.println(result);
            }
        });
        resultObject.addReturnDesc("获取成功");
        resultObject.addResultData(deferredResult);
        resultObject.addReturnCode(BasicConstants.RETURN_CODE_SUCCESS);
        return resultObject.toJson();
    }
}
