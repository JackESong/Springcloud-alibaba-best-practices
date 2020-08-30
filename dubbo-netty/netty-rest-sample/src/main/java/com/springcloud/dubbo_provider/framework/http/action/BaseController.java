package com.springcloud.dubbo_provider.framework.http.action;

import com.springcloud.dubbo_provider.framework.entity.Request;

public interface BaseController extends Runnable{


    public String doAction(Request request);

}
