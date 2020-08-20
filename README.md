# Springcloud-alibaba-sample
Springcloud-alibaba-sample 整合实验
## 1.dubbo-nacos
版本情况：
Springcloud-alibaba 2.1.1 对应 dubbo 2.7.6 Springboot 2.2.5 
## 2.dubbo-nacos-zipkin 弃用 改为skywalking 
版本情况：
Springcloud-alibaba 2.1.1 对应 dubbo 2.7.6 Springboot 2.2.5
dubbo-nacos-zipkin 弃用 改为skywalking  通过agent代理方式即可实现服务监控
推荐组合：skywalking+influxdb
influxdb cpu过高问题待处理。
## 3.dubbo-nacos-gateway
整合网关实验
gateway网关 不能引入starter-web pom.xml文件注意一下
测试:
http://localhost/api-a/hello
http://localhost/api-b/hello
## 4.dubbo-docker
简易脚本制作

## 5.dubbo-sentinel
官方文档案例
https://github.com/alibaba/Sentinel/tree/master/sentinel-demo
完成限流测试
## 6.dubbo-kafka
已制作
可视化
kafka-eagle
kafka集群
## 7.dubbo-rocketmq
http://rocketmq.apache.org/
https://github.com/apache/rocketmq/tree/master/docs/cn
https://github.com/apache/rocketmq/blob/master/docs/cn/RocketMQ_Example.md
可视化
 https://github.com/apache/rocketmq-externals/ 
 
## 8.dubbo-redisson
自定义分布式锁


## 9.dubbo-sharingshare
进行中
> springboot-sharingjdbc-sample：示列  整合自 shardingJDBC-master 进行功能校验: 完成更新

> jdbc-read-write-springboot：Spring Boot版 Sharding JDBC 读写分离示列 : 完成更新

> jdbc-sharding-table-springboot：Spring Boot版 Sharding JDBC 不分库，只分表案例 : 完成更新

> jdbc-db-sharding-springboot：Spring Boot版 Sharding JDBC 垂直拆分（不同的表在不同的库中） : 完成更新

> jdbc-db-read-write-sharding-springboot：Spring Boot版 Sharding JDBC 垂直拆分（不同的表在不同的库中）+ 读写分离

> jdbc-sharding-table-read-write-springboot：Spring Boot版 Sharding JDBC 不分库，只分表+读写分离案例

> jdbc-db-sharding-table-springboot：Spring Boot版 Sharding JDBC 分库分表案例

> jdbc-db-sharding-table-read-write-springboot：Spring Boot版 Sharding JDBC 分库分表+读写分离案例

> jdbc-db-sharding-table-read-write-range-group-springboot：Spring Boot版 Sharding JDBC 分库分表+读写分离案例(范围分表+取模=无限扩容)

代码整合自 https://github.com/yinjihuan/sharding-jdbc 进行功能校验

## 10.dubbo-mqsql/oracle/sqlserver
规划中
## 11.dubbo-seata
规划中
## 12.dubbo-mongodb
规划中
## 13.themeleaf
规划中
## 14.dubbo-nacos-config
规划中
## 15.dubbo-hystrix
规划中
## 16.dubbo-apollo
规划中
## 17.dubbo-测试
规划中
## 18.dubbo-job
规划中
## 19.dubbo-minio
规划中
## 20.dubbo-ceph
规划中
## 21.dubbo-future
规划中



