# 快速通用项目  

## 技术  
1. Spring Cloud  
2. Spring boot  
3. Mybatis plus  
4. swagger2  
5. druid  

## 项目结构   
```
tdd  
|  
|—— cloud  -- 公共基础组建
|    |  
|    |—— eureka -- 服务注册中心  
|    |  
|    |—— configer -- 配置中心  
|    |  
|    |—— gateway -- 网关中心  
|    |  
|    |—— oauth-server -- oauth认证服务器  
|    |
|    |—— consul-joker -- 服务注册中心
|  
|—— cms -- 后台管理系统  
|  
|—— common -- 公共基础包  
|      |
|      |—— base -- 公共基础jar包
|      |
|      |—— db  -- 执行 mybatis plus 代码自动生成工具类
|      |
|      |—— oauth -- 认证客户端基础包  
|  
|—— demo  -- 测试demo
|    |
|    |—— oauth-client -- 认证客户端
|
|—— frontend  -- 前端项目
|    |
|    |—— store -- 听多多手机端商城  
|
|
|—— mall -- 商城项目
|    |  
|    |—— service-goods -- 商品微服务  

```

## 项目介绍  
**eureka：服务注册中心**  
> http://localhost:1025/eureka-server/  

**com.cloud.oauth.config-server：配置服务器**  
> http://localhost:1001/  

**com.rb.oauth-server：认证服务器**  
> http://localhost:1003  

**com.rb.oauth-client: 认证客户端**
> http://localhost:1004  
> 访问该地址会自动跳转到认证服务器进行授权登录，登录成功后跳转至index.html页面。

**com.rb.common：公共基础包**  

**com.rb.common-db：数据库公共基础包**
> 引入该jar包，修改MyBatisPlusGeneratorUtil.class配置，执行main方法自动生成文件。  

**com.rb.oauth：认证客户端基础包**  

**com.mall.cms：后台管理系统**  
> http://localhost:8000  
> 框架：xadmin layui  
> 注意：layui与vue有冲突，最好使用jquery。

**com.cloud.gateway：网关中心**
> http://localhost:1002
> 所有访问都需经过网关中心，网关中心经过过滤鉴权后，路由到具体微服务。

**com.mall.service-goods：商品微服务**  
> http://localhost:8800  
> 接口文档：/swagger-ui.html  

**com.frontend.store：手机web端商城**  
> http://localhost:8010  
> 项目目前采用sui前端ui框架  



## 项目编码  
1. MyBatisPlusGeneratorUtil自动生成entity、mapper、service、controller等文件。  
2. 需要权限认证的，导入com.rb.oauth包，配置MyShiroConfiguration的shiroFilter和Oauth2AuthenticationFilter。


## 注意事项  
1. 服务提供者要优先于服务消费者启动，不然会报找不到这个服务。  

## 项目部署  
1. 安装nginx  
> http://www.nginx.cn/4723.html  
2. 创建网络
> docker network create -d bridge my-net
3. 运行eureka文件夹中的docker-compose  
4. 运行cloud文件夹中的docker-compose  
5. 运行mall文件夹中的docker-compose  
> 访问数据库填写IP地址  
> 访问容器组件填写容器名称  


## 疑难杂症  



