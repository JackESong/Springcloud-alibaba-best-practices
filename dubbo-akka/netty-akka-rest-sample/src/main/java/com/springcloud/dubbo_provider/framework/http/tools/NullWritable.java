package com.springcloud.dubbo_provider.framework.http.tools;

import java.io.Serializable;

/**
 * 服务器可能返回空的处理
 */
public class NullWritable implements Serializable {


    private static NullWritable instance = new NullWritable();

    private NullWritable() {
    }

    public static NullWritable nullWritable() {
        return instance;
    }
}
