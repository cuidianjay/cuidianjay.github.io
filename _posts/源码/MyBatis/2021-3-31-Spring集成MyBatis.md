---
title: Spring集成MyBatis
date: 2021-3-31 14:12:52 +0800
categories: [源码, Mybatis]
tags: [Mybatis, 源码]
math: true
image: 
---



### 知识点

1. Spring如何继承MyBatis？

   1. @MapperScanner注解指定Mapper包；
   2. 利用@Bean注册SqlSessionFactoryBean

2. 集成SqlSessionFactoryBean的作用

   用来构建SqlSessionFactory，用来读取MyBatis的信息设置到Configuration中。

3. MyBatis怎么集成Spring声明式事务？

   MyBatis拿到Spring声明事务 开启事务时创建的Connection，在构建SqlSessionFactory时，会new一个SpringManagedTransactionFactory事务工厂，创建一个新的SpringManagedTransaction，当mybatis获取Connection时，就会从SpringManagedTransaction.getConnection()中获取Connection，而这个Connection就是TransactionSynchronizationManager事务同步管理器中的本地线程变量ThreadLocal中的Connection。

4. MyBatis中的Mapper怎么注入到IOC容器中的（**重点**）？

   1. 扫描：通过继承ClassPathBeanDefinitionScanner重写isCandidateComponent()方法扫描mapper所在包路径，

      得到BeanDefinitionHolder集合；

   2. 修改BeanDefinition中的BeanClass替换为FactoryBean（因为接口没有办法实例化），通过getObject()方法，传入Class对象使用JDK动态代理实现Mapper接口；

      > 实现了FactoryBean的Class在被Spring创建时，会将getObject()方法返回的实例放入ioc容器中。

   3. 实现BeanDefinitionRegistryPostProcessor将BeanDefinition注册进IOC容器；

   

5. 

6. 

   