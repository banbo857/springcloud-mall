### springcloud springboot mybatis redis fastdfs

### gateway feign eureka hystrix

## 1.禁止用户直接访问服务，通过gateway网关路由

## 2.gateway 添加请求头 From-gateway

## 3.子服务过滤器检查from请求头

## 4.分布式session 存入redis中 服务调用时用sessionId查找

## 5.feign调用子服务时丢失Cookie和Header

> ###### 1.关闭`hystrux`熔断
>
> ###### 2.更改隔离策略`Thread - > SEMAPHORE`
>
> ###### 3.自定义并发策略 继承 HystrixConcurrencyStrategy 重写wrapCallable方法
>
> ###### wrapCallable方法拿到RequestContextHolder.getRequestAttributes()，也就是我们想传播的对象;
>
> ###### 在wrappedCallable类中，我们将要传播的对象作为成员变量，并在其中的call方法中，为静态方法设值。
>
> ###### 这样，在Hystrix包裹的方法中，就可以使用
>
> ###### RequestContextHolder.getRequestAttributes()获取到相关属性――也就是说，
>
> ###### 可以拿到RequestContextHolder中的 ThreadLocal属性.
>
> #####     

## 6.跨域

#### Gateway跨域配置

> ```java
> globalcors:
>   cors-configurations: #跨域配置
>     '[/**]':
>       allowedOrigins: '*' #允许跨域的域名，可以用*表示允许任何域名使用
>       allowedMethods: '*' #允许任何方法（post、get等）
>       allowedHeaders: '*' #允许任何请求头
>       allowCredentials: true #允许携带cookie
>       maxAge: 18000 #预检请求的有效期，单位为秒
> default-filters:
>   - DedupeResponseHeader=Vary Access-Control-Allow-Origin Access-Control-Allow-Credentials, RETAIN_FIRST
> ```

#### 前端ajax请求配置

```html
            xhrFields: {
withCredentials: true
},
crossDomain: true,
```

#### Cookie设置domain="127.0.0.1"、path="/"

## 7.分布式文件系统fastdfs

1.docker -> fastdfs

2.配置

```
@Import(FdfsClientConfig.class)
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
```

```
fdfs:
  connect-timeout: 60
  so-timeout: 60
  thumb-image:
    width: 150
    height: 150
  tracker-list:
      - 192.168.8.10:22122
```

```
<dependency>
    <groupId>com.github.tobato</groupId>
    <artifactId>fastdfs-client</artifactId>
</dependency>
```