package com.springcloud.dubbo_provider.project.route;

import com.springcloud.dubbo_provider.framework.entity.Request;
import com.springcloud.dubbo_provider.project.controller.ErrorController;
import com.springcloud.dubbo_provider.framework.http.action.BaseController;
import com.springcloud.dubbo_provider.project.controller.TokenController;

public class DispatherActionManager {

    public static BaseController getAction(Request req){

        if(req.getHttpPath().startsWith("/getToken")){
            return new TokenController();
        }
        return ErrorController.getInstance();
    }

}
