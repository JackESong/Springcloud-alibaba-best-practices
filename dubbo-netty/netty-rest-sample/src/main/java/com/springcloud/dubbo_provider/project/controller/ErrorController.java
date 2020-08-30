package com.springcloud.dubbo_provider.project.controller;


import com.springcloud.dubbo_provider.framework.e.BasicConstants;
import com.springcloud.dubbo_provider.framework.entity.Request;
import com.springcloud.dubbo_provider.framework.entity.ResultMsg;
import com.springcloud.dubbo_provider.framework.http.action.BaseController;

public class ErrorController implements BaseController {

    private static ErrorController errorAction = new ErrorController();

    private ResultMsg resultObject = new ResultMsg();

    public static ErrorController getInstance() {
        return errorAction;
    }

    @Override
    public String doAction(Request request) {
        resultObject.addReturnDesc("request uri is invalid ...");
        resultObject.addReturnCode(BasicConstants.RETURN_CODE_ERROR);
        return resultObject.toJson();
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub

    }

}
