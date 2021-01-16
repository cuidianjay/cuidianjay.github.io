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
- Exchanger

#### CountDownLatch

- 介绍

- 应用场景

  - 计数器

    ~~~java
    int count = 10;
    CountDownLatch countDownLatch = new CountDownLatch(count);
    for(int i = 0; i < 10; i++){
        new Thread({
            run(){
                //do something
                //count-1
                countDownLatch.countDown();
            }
        }).start();
    }
    //主线程在此阻塞，等待所有线程执行完成,count=0
    countDownLatch.await();
    ~~~

    

  - 模拟并发

    ~~~java
    int count = 1;
    CountDownLatch countDownLatch = new CountDownLatch(count);
    for(int i = 0; i < 10; i++){
        new Thread({
            run(){
                //所有线程都在此阻塞,等待countDown()信号
                countDownLatch.await();
                //do something
            }
        }).start();
    }
    //count-1=0 唤醒await()阻塞的线程
    countDownLatch.countDown();
    ~~~

    除了CountDownLatch，CyclicBarrier也可以实现模拟并发，不过CyclicBarrier可以重复执行
    
    ~~~java
    int count = 11;
    CyclicBarrier cyclicBarrier = new CyclicBarrier(count);
    for(int i = 0; i < 10; i++){
        new Thread({
            run(){
                //等待11个线程调用cyclicBarrier.await()方法
                cyclicBarrier.await();
                //do something
            }
        }).start();
    }
    //第11个线程await() 所有线程开始执行后续操作
    cyclicBarrier.await();
    ~~~
  
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

  - 模拟并发

- 原理

#### Exchanger

- 介绍

  可以在线程间交换数据。

- 实例代码

  ~~~java
  public static void main(String []args) {
      final Exchanger<Integer> exchanger = new Exchanger<Integer>();
      for(int i = 0 ; i < 4 ; i++) {
          final Integer num = i;
          new Thread() {
              public void run() {
                  System.out.println("我是线程：Thread_" + this.getName() + "我的数据是：" + num);
                  try {
                      Integer exchangeNum = exchanger.exchange(num);
                      Thread.sleep(1000);
                      System.out.println("我是线程：Thread_" + this.getName() + "我原先的数据为：" + num + " , 交换后的数据为：" + exchangeNum);
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }
              }
          }.start();
      }
  }
  
  //运行结果
  /*
  我是线程：Thread_Thread-0我的数据是：0
  我是线程：Thread_Thread-3我的数据是：3
  我是线程：Thread_Thread-2我的数据是：2
  我是线程：Thread_Thread-1我的数据是：1
  我是线程：Thread_Thread-0我原先的数据为：0 , 交换后的数据为：3
  我是线程：Thread_Thread-1我原先的数据为：1 , 交换后的数据为：2
  我是线程：Thread_Thread-2我原先的数据为：2 , 交换后的数据为：1
  我是线程：Thread_Thread-3我原先的数据为：3 , 交换后的数据为：0
  */
  ~~~

  