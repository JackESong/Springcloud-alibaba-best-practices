package com.springcloud.dubbo_provider.project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class SampleTest {

    @Resource
    DistributedLocker distributedLocker;

    private final static Logger log = LoggerFactory.getLogger(SampleTest.class);

    public String test(String orderType, String mSystemId, int length) {
        String key = "a_" + orderType;
        String nextNumber = "";
        try{
            // 加锁 最多等待3秒，上锁以后10秒自动解锁
            log.info("尝试加锁");
            if (distributedLocker.tryLock(key, TimeUnit.SECONDS,3,10)){
                log.info("加锁成功");
                // 计划
            }else{
                log.info("尝试加锁失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            log.info("解锁失败");
        }finally {
            // 解锁
            distributedLocker.unlock(key);
            log.info("解锁成功");
        }
        return nextNumber;
    }


}
