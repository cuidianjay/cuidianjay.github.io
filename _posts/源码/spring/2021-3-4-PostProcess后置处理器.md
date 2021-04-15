---
title: PostProcess后置处理器
date: 2021-3-4 14:17:18 +0800
categories: [源码, Spring, PostProcess后置处理器]
tags: [Spring, 源码, PostProcess后置处理器]
math: true
image: 
---

### BeanFactoryPostProcess & BeanPostProcess

BeanFactoryPostProcess后置处理器主要对BeanDefinition进行扩展，

BeanPostProcess后置处理器主要对Bean进行扩展。

### Full Lite

Full配置类 @Configuration，会创建CGlib代理

Lite配置类 @Bean @Component

### @Configuration加与不加的区别

ConfigurationClassPostProcessor在解析@Configuration配置类时，会对类进行cglib动态代理，如果里面有@Bean，存在new Object()，会将new Object() 替换为getBean()，这样就不会重复创建对象，保证Ioc容器里面的Bean是单例的。

不加，Bean之间的引用就是普通的方法调用。

### 同名Bean

通过Scanner扫描，同名的Bean会报错，因为在解析@Component注解时会判断是否已经解析过该Bean，如果是，就会报错。

如果一个是@Component，一个是@Bean，那么@Bean里面的Bean会覆盖@Component，因为@Bean后注册Bean定义。