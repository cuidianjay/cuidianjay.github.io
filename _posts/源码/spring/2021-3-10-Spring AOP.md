---
title: Spring AOP
date: 2021-3-10 10:34:41 +0800
categories: [源码, Spring, AOP]
tags: [Spring, 源码, AOP]
math: true
image: 
---

### AOP各个角色

#### Target 目标

需要增强的目标

#### Aspect 切面



#### Advice 通知





### Spring AOP与AspectJ关系

Spring AOP只用到了AspectJ的切点表达式解析，延用了@Aspect、@Before、@After等概念。



### Spring AOP实现方式演变

#### Spring 1.2 基于接口的配置

通过ProxyFactoryBean

责任链设计模式，依次调用Before、After增强方法。

> 责任链的各个实现类都必须是同一个抽象；
>
> 循环或者递归调用实现方法。

#### 注解方式实现

1. 解析切面
2. 生成动态代理
3. 方法调用

#### XML配置方式



#### AspectJ实现方式



### Spring AOP实现原理

#### 使用哪种动态代理

1. 如果Bean是接口或者本身就是JDK动态代理对象，使用JDK动态代理；
2. Bean没有实现接口，或者指定了@EnableAspectJAutoProxy(proxyTargetClass = true)，使用CGlib动态代理。

#### JDK动态代理与CGLIB动态代理区别

- JDK动态代理的Bean，在本类代理的方法调用同类其他代理方法不会重复增强

  想要重复增强，需要以下处理@EnableAspectJAutoProxy(exposeProxy = true)，将JDK代理对象暴露出来

  ``` java
  public class JDKProxyTest {
  
  	public void method1(){
  		System.out.println("method1");
  	}
  
  	public void method2(){
          //直接调用不会重复增强
  		method1();
          //会重复增强，因为JDK动态代理对象会放在AopContext的ThreadLocal<Object> currentProxy = new NamedThreadLocal<>("Current AOP proxy");中
  		((JDKProxyTest)AopContext.currentProxy()).method1();
  		System.out.println("method2");
  	}
  }
  ```

- CGLIB代理的Bean，在本类调用其他方法会重复增强。因为CGLIB代理对象每次调用方法都会重新路由找到对应方法，生成代理对象。

- JDK代理会根据接口生成1个Proxy.class，使用反射方式调用具体方法

  实现InvocationHandler接口，invoke()方法增强代码

- 根据接口具体实现类生成代理，继承实现类

  实现MethodInterceptor接口，intercept()方法增强代码

- 效率

  - 创建：CGLIB需要创建多个.class文件，所以慢于JDK动态代理，不过代理过程在Spring容器启动过程中完成，所以影响不大
  - 调用：CGLIB直接调用方法，JDK反射调用方法，所以CGLIB效率优于JDK



