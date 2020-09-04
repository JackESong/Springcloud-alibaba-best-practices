package com.springcloud.dubbo_provider.project.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsyncTask {

    @Async("taskExecutor")
    public void tesTask(int i){
        System.out.println(Thread.currentThread().getName()+"-----"+i);
    }

    @Async("taskExecutor")
    public void stringTask(String str){
        System.out.println(Thread.currentThread().getName()+str);
    }
}

