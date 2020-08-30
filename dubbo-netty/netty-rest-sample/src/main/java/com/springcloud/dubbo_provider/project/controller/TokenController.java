package com.springcloud.dubbo_provider.project.controller;

import com.springcloud.dubbo_provider.framework.e.BasicConstants;
import com.springcloud.dubbo_provider.framework.entity.Request;
import com.springcloud.dubbo_provider.framework.entity.ResultMsg;
import com.springcloud.dubbo_provider.framework.http.action.BaseController;

import java.util.UUID;

public class TokenController implements BaseController {

    private ResultMsg resultObject = new ResultMsg();

    @Override
    public String doAction(Request request) {
        resultObject.addReturnDesc("获取成功");
        resultObject.addResultData(UUID.randomUUID().toString());
        resultObject.addReturnCode(BasicConstants.RETURN_CODE_SUCCESS);
        return resultObject.toJson();
    }

    @Override
    public void run() {
    }
}
