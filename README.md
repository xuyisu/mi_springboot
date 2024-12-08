# 仿小米商城后端服务
## 介绍

模拟小米官网的后端服务

项目运行环境

| jdk          | 1.8+   |
| ------------ | ------ |
| maven        | 3.6.3+ |
| springboot   | 2.*    |
| mybatis-plus | 3.2.0+ |
| mysql        | 5.7+   |
| redis        | 6.2.1  |



## 后端运行先配置数据库（mysql 和redis）

[代码地址Gitee](https://gitee.com/gitxys/mi_springboot)

[代码地址GitHub](https://github.com/xuyisu/mi_springboot)

```
server:
  port: 8081
spring:
  application:
    name: mi-springboot
  #数据库配置  start
  datasource:
    druid:
      type: com.alibaba.druid.pool.DruidDataSource
      url: jdbc:mysql://localhost:3306/mi-mal?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true&useSSL=false
      username: root
      password: 123456
      driver-class-name: com.mysql.jdbc.Driver
      # 初始化配置
      initial-size: 3
      # 最小连接数
      min-idle: 3
      # 最大连接数
      max-active: 15
      # 获取连接超时时间
      max-wait: 5000
      # 连接有效性检测时间
      time-between-eviction-runs-millis: 90000
      # 最大空闲时间
      min-evictable-idle-time-millis: 1800000
      test-while-idle: true
      test-on-borrow: false
      test-on-return: false
      validation-query: select 1
      # 配置监控统计拦截的filters
      filters: stat
      stat-view-servlet:
        url-pattern: /druid/*
        reset-enable: false
        login-username: admin
        login-password: admin
        allow: 127.0.0.1
        enabled: true   #默认值为true，即打开监控页面，但存在泄漏信息的风险，所以修改为false
      web-stat-filter:
        url-pattern: /*
        exclusions: "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*"
  #redis 配置
  redis:
    host: localhost
    port: 6379
    password: 123456
    database: 10
    jedis:
      pool:
        max-active: 8
        max-wait: 5s
        min-idle: 0
        max-idle: 8
```



点击启动类启用服务

```
@SpringBootApplication
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class);
    }
}
```
## 其他语言后端地址

| 名称                       | Gitee                                      | Github                                      |
| -------------------------- | ------------------------------------------ | ------------------------------------------- |
| mi_springboot (Java)       | https://gitee.com/gitxys/mi_springboot     | https://github.com/xuyisu/mi_springboot     |
| mi-beego (Golang)          | https://gitee.com/gitxys/mi-beego          | https://github.com/xuyisu/mi-beego          |
| mi-gin (Golang)            | https://gitee.com/gitxys/mi-gin            | https://github.com/xuyisu/mi-gin            |
| mi_django (Python)         | https://gitee.com/gitxys/mi_django         | https://github.com/xuyisu/mi_django         |
| mi_fastapi (Python)        | https://gitee.com/gitxys/mi_fastapi        | https://github.com/xuyisu/mi_fastapi        |
| mi_koa_nodejs (nodejs)     | https://gitee.com/gitxys/mi_koa_nodejs     | https://github.com/xuyisu/mi_koa_nodejs     |
| mi_express_nodejs (nodejs) | https://gitee.com/gitxys/mi_express_nodejs | https://github.com/xuyisu/mi_express_nodejs |
| mi_egg_nodejs (nodejs)     | https://gitee.com/gitxys/mi_egg_nodejs     | https://github.com/xuyisu/mi_egg_nodejs     |
|                            |                                            |                                             |



## 前端启动

项目地址https://gitee.com/gitxys/mi_vue

[代码地址Gitee](https://gitee.com/gitxys/mi_vue)

[代码地址GitHub](https://github.com/xuyisu/mi_vue)

控制台先安装依赖包

```
npm install 
```

然后运行下面代码即可启动

```
npm run serve
```

## 页面介绍

浏览器输入http://localhost:8080 将看到一下页面

![](images/index.png)

登录:**用户名/密码**  admin/123456

![image-20211219223115929](images/login.png)

购物车

![image-20211219223220837](images/cart.png)

订单确认

![image-20211219223323684](images/order-confirm.png)

订单结算(彩蛋！！！！   这里的结算做了特殊处理)

![image-20211219223406482](images/pay.png)

订单列表

![image-20211219223507791](images/order.png)





亲，留个star 吧
