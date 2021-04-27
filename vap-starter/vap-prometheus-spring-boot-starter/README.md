## prometheus与springboot监控

本地代码目录：D:\VRV\prisonBreak\springboot\springboot-actuator

```shell
D:\VRV\prisonBreak\actuator\prometheus-2.20.1.windows-amd64\prometheus-2.20.1.windows-amd64
D:\VRV\prisonBreak\actuator\grafana-7.0.6.windows-amd64\grafana-7.0.6

参考文档：https://zhuanlan.zhihu.com/p/106036485
```

> 整体结构：
>
> - springboot（micrometer）产生监控数据。
> - Prometheus 获取 springboot 应用的监控数据，存储，并提供数据查询服务。
> - Grafana 对接 Prometheus 数据源，调用其数据查询服务，用专业的仪表盘 UI 进行展示。

### 1. springboot依赖添加pom.xml

####  1.1 增加micrometer-regitry-prometheus

为了让springboot应用和prometheus集成， 你需要增加micrometer-regitry-prometheus依赖

```shell
<dependency>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>

<!-- 集成springboot与prometheus -->
<!-- http://localhost:8081/actuator/prometheus -->
<dependency>
    <groupId>io.micrometer</groupId>
    <artifactId>micrometer-registry-prometheus</artifactId>
    <version>LATEST</version>
</dependency>
```

添加上述依赖项之后，springboot将会自动配置  PrometheusMeterRegistry。 Prometheus可以抓取的格式收集和导出指标数据。 

所有的相关数据， 都会在Actuator的`/prometheus` 断点暴露出来。 Prometheus可以抓取该端点以定期获取度量标准数据。 



Actuator的`/prometheus`的端点，这个springboot版本是2.x 以上版本

访问： http://localhost:8080/actuator/prometheus

springboot1.x版本

访问： http://localhost:8080/prometheus



#### 1.2  修改prometheus.yml文件

```shell
scrape_configs:
  # The job name is added as a label `job=<job_name>` to any timeseries scraped from this config.
  - job_name: 'prometheus'

    # metrics_path defaults to '/metrics'
    # scheme defaults to 'http'.

    static_configs:
    - targets: ['localhost:9090']

  - job_name: 'springboot-actuator'
    scrape_interval: 5s
    metrics_path: '/actuator/prometheus'
    static_configs:
    - targets: ['localhost:8081']
      labels: {
            "instance": "actuator-A",
            "service": "actuator-A-service"
        }
```





在Spring Boot Actuartor 健康检查、度量，指标收集和监控。 使用欧冠Micrometer为java平台上的性能数据收集一个通用的API， 应用程序只需要使用这个通用的Api来收集性能指标即可。 

插件及说明： 

Java/JVM: [Micrometer Prometheus Registry](https://micrometer.io/docs/registry/prometheus)



### 2. springboot服务添加

#### 2.1主配置文件添加

```powershell
server:
  port: 8081
# actuator 只开放了actuator， health-path， health， info   其他需要手动开放
management:
  endpoints:
    web:
      exposure:
        include: '*'
# include: health,info
  endpoint:
    health:
      show-details: always
# 名称定义
  metrics:
    tags:
      application: ${spring.application.name}

logging:
  level:
    root: info
    com.example.actuator: error

spring:
  application:
    name: springboot-actuator
```

未添加如上配置，springboot actuator只暴露了actuator， health-path， health， info  等相关信息。



#### 2.2 springboot中新增配置类

springboot中新增配置类，进行数据发送到prometheus中， 引用参考`https://micrometer.io/docs/registry/prometheus`

```shell
 @Bean
 MeterRegistryCustomizer<MeterRegistry> configurer(@Value("${spring.application.name}") String applicationName){
 	return registry -> registry.config().commonTags("application", applicationName);
 }
```

> 如果能够访问浏览器,则成功
http://localhost:9560/actuator/prometheus
> 访问prometheus的地址
> http://localhost:9092/



### 3.新增dashboard

https://micrometer.io/docs/registry/prometheus

```shell
# 访问grafana地址
http://localhsot:3000/

# 设置grafana的datasource

# 设置json，可以copyid
```



https://grafana.com/grafana/dashboards/4701



第一步： 设置dashboard

>![image-20200826194908548](C:\Users\hp\AppData\Roaming\Typora\typora-user-images\image-20200826194908548.png)
>
>

第二步:  输入4701， 然后Load

>![image-20200826194949124](C:\Users\hp\AppData\Roaming\Typora\typora-user-images\image-20200826194949124.png)
>
>



第三步:  保存按钮

>
>
>![image-20200826195359952](C:\Users\hp\AppData\Roaming\Typora\typora-user-images\image-20200826195359952.png)



>
>
>![image-20200826195303595](C:\Users\hp\AppData\Roaming\Typora\typora-user-images\image-20200826195303595.png)



参看图像界面：

![image-20200827065004066](C:\Users\hp\AppData\Roaming\Typora\typora-user-images\image-20200827065004066.png)



![image-20201014194241531](./prometheus.assets/image-20201014194241531.png)

## Prometheus + Granfana + Altermanager实现监控告警

> prometheus是一款系统和服务兼容软件。 
>
> Grafana是一款数据看板工具
>
> Alarmmanager是Prometheus官方提供的报警工具，用于接收Prometheus推送过来的报警信息。 





文档引用：

- https://prometheus.io/docs/instrumenting/exporters/
- https://micrometer.io/docs/registry/prometheus
- https://cloud.tencent.com/developer/article/1508319

