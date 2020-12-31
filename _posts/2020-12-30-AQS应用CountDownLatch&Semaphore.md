---
title: AQS应用之CountDownLatch&Semaphore&CyclicBarrier
date: 2020-12-30 15:29:02 +0800
categories: [并发编程, AQS]
tags: [并发编程, 线程通信, AQS]
math: true
image: 
---

### 线程通讯

- BlockingQueue  同步队列
- CountDownLatch
- Semaphore
- CyclicBarrier

#### CountDownLatch

- 介绍

- 应用场景

- 原理

  

#### Semaphore

- 介绍

- 应用

  - 应用场景

    - 限流 (Hystrix实现1：线程池，2：semaphore信号量)

  - 常用方法

    ```java
    acquire();
    acquire(int permits);
    tryAcquire();
    tryAcquire(long timeout, TimeUnit unit);
    tryAcquire(int permits, long timeout, TimeUnit unit);
    acquireUninterruptibly();
    acquireUninterruptibly(int permits);
    ```

  - 示例代码

    ~~~java
    //声明10个许可证的信号量，并发量为10
    Semaphore Semaphore = new Semaphore(10);
    //请求许可证，默认请求1个  semaphore.acquire(2) 请求2个许可证
    semaphore.acquire();
      
    doSomething();
      
    //释放证，默认释放1个  semaphore.release(2) 释放2个许可证
    semaphore.release(); 
    ~~~

    - 

- 原理

  - 传入permits信号量值，设置AQS state值，默认非公平锁，基于AQS共享模式Node节点实现，每个线程都可以去拿许可证，减少state

    ```java
    public Semaphore(int permits) {
        sync = new NonfairSync(permits);
    }
    //fair == true 公平锁
    public Semaphore(int permits, boolean fair) {
        sync = fair ? new FairSync(permits) : new NonfairSync(permits);
    }
    ```



#### CyclicBarrier

- 介绍
- 应用场景
- 原理