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
## 3.dubbo-nacos-gateway
整合网关实验
gateway网关 不能引入starter-web pom.xml文件注意一下
测试:
http://localhost/api-a/hello
http://localhost/api-b/hello
