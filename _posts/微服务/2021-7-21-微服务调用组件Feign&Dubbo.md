### Feign与OpenFeign

- Feign是Netflix开源的
- OpenFeign是Spring Cloud OpenFeign对Feign的增强版，使其支持Spring MVC注解，还整合了Ribbon和Eureka，从而使用Feign更方便。



### 为什么使用Feign，而不用Ribbon

#### Ribbon+RestTemplate

1. 不优雅
2. 代码中充斥着大量的url+restTemplate调用

#### Feign进行微服务调用

1. 可读性更好
2. 编码更优雅
3. 就像本地Service方法调用一样，不需要关注底层原理

### 单独使用Feign





### 注意

- 链接与请求超时时以Feign为准
- Client层 FeignAutoConfiguration自动配置类

