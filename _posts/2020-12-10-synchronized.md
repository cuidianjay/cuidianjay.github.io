---
title: synchronized原理
date: 2020-12-10 13:43:20 +0800
categories: [并发编程, java]
tags: [并发编程, 锁]
math: true
image: 
---

### synchronized发展历史

​	在jdk1.6之前，synchronized锁的效率较ReentrantLock锁非常低，这是因为synchronized直接锁住java对象，每个新建的对象在jvm中会对应一个Monitor管程对象，而monitor依赖于底层系统的Mutex互斥量，Mutex互斥量由操作系统维护，其中linux系统中有Pthread线程库，其中有线程的阻塞api，但jvm是运行在用户空间的，想要阻塞必须切换回内核空间，这个切换是一个重操作，所以效率低下。

​	在jdk1.6及以后，引入了synchronized锁升级机制，优化锁效率。

### 锁升级

![synchronized锁升级](/assets/img/study/synchronized-upgrade.png)

### 加锁粒度：

* 代码块

  ~~~java
  public static final Object lockObj = new Object();
  synchronized(lockObj){
      //do something
  }
  ~~~

  锁在lockObj对象

* 普通方法

  ~~~java
  public class LockDemo{
  	public synchronized void lockDo(){
          int res = this.res;
      }   
  }
  
  ~~~

  锁在当前实例对象this

* 静态方法

  ~~~java
  public class LockDemo {
  	public static synchronized void lockDo(int res){    
  	}
  }
  ~~~

  锁在当前类Class，LockDemo. Class

  **注意** 千万不要在项目中出现下面的语句，因为这个println()方法是同步的，而且锁定的out也就是java.io.PrintStream，在System中是静态常量，这意味着所有的System.out.println()竞争一个锁！！！
  
  ~~~java
  System.out.println();
  ~~~

### 锁机制

​	java对象内存布局由对象头、实例数据区、对其填充位组成，当一个对象被加锁时，对象的锁状态保存在对象头信息中，



​	锁状态表格

![markword](/assets/img/study/object_markword.png)

~~~java
//关闭偏向锁 -XX:-UseBiasedLocking
//无同步块
00000001 00000000 00000000 00000000
//有同步块
10111000 11110010 01101110 00000010
//开启偏向锁 （匿名偏向，可偏向状态）
//无同步块
00000101 00000000 00000000 00000000
//有同步块
00000101 01011000 11111010 00000010
~~~

* 匿名偏向，可偏向状态，代表对象已经准备好转为偏向锁，此时并没有记录线程id
* 调用偏向锁的hashCode()方法，会导致该对象升级为轻量级锁

### 锁粗化

提升程序性能

例如下面这种代码

~~~java
synchronized (object){
    //do something1
}
synchronized (object){
    //do something2
}
~~~

锁粗化之后

~~~java
synchronized (object){
    //do something1
    //do something2
}
~~~

### 锁消除

去除不可能存在共享竞争的资源的锁，依据线程逃逸分析的数据支持。

例如

~~~java
private void method(){
    Object o = new Object();
    synchronized (o){
    	//do something
	}
}

~~~

锁消除优化后

~~~java
private void method(){
    Object o = new Object();
    //do something
}
~~~

### 线程逃逸分析

java对象 

八大基本数据类型->标量  标量替换

对象->聚合量

判断当前对象的引用会不会逃逸出该线程。