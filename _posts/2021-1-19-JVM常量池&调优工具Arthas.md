---
title: JVM调优工具&常量池
date: 2021-1-19 14:26:17 +0800
categories: [JVM, JVM调优工具, Arthas]
tags: [JVM, Arthas, 常量池]
math: true
image: 
---

### Arthas

[Arthas官网文档](https://arthas.aliyun.com/doc/)，[Arthas Github](https://github.com/alibaba/arthas)

 



### GC日志

#### GC日志需要的参数

- -Xloggc:./gc-%t.log

  GC日志文件路径

- -XX:+PrintGCDetails

  开启GC日志打印 

- -XX:+PrintGCDateStamps

  GC日期 

- -XX:+PrintGCTimeStamps

  GC时间戳 

- -XX:+PrintGCCause

  GC原因   

- -XX:+UseGCLogFileRotation

  开启滚动打印GC日志 

- -XX:NumberOfGCLogFiles=10

  保留10个GC日志文件 

- -XX:GCLogFileSize=100M

  每个GC日志大小100M              

当项目较大时，项目启动慢，可能是因为没有设置元空间大小（默认21M），导致元空间被放满触发Full GC，项目启动变慢。

### 常量池



#### 常量池位置

- jdk1.6及之前：有永久代，运行时常量池在永久代，运行时常量池包含字符串常量池
- jdk1.7：有永久代，字符串常量池从永久代中的运行时常量池分离到堆中
- jdk1.8及之后：无永久代，运行时常量池在元空间，字符串常量池在堆中

#### 三种字符串操作（jdk>=1.7）

- 直接赋值字符串

  ~~~java
  String str = "abc";
  ~~~

  这种方式创建的字符串对象只存在于常量池中。

  在创建对象之前，会equals("abc")方法在字符串常量池中查找是否存在相同的对象，存在就直接返回该对象在字符串常量池中的引用，否则，在字符串常量池中创建一个常量对象，并返回该引用。

- new String()

  ~~~java
  String str = new String("abc");
  ~~~

  在堆、常量池中都会存在这个对象，字符串常量池中没有就创建，然后返回该对象在堆中的引用。

  先看字符串常量池中是否存在"abc"这个常量对象，没有就创建一个，然后再去堆中创建对象，

  有的话，就直接在堆中创建对象。

- intern方法

  ~~~java
  String str = new String("abc");
  String str1 = str.intern();
  //str == str1,false
  System.out.println(str == str1);
  ~~~

  首先查看字符串常量池中是否存在equals此String对象(str)的字符串，存在则返回此字符串，否则intern()返回的引用为当前对象(str)。







