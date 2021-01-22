---
title: JVM对象创建与内存分配机制
date: 2021-1-12 13:53:39 +0800
categories: [JVM, 垃圾收集器, ParNew&CMS]
tags: [垃圾收集器, JVM, ParNew, CMS]
math: true
image: 
---

### 垃圾收集算法

#### 分代收集理论

根据各个年代选择合适的垃圾收集算法。 

#### 标记-复制算法



缺点：浪费空间



#### 标记-整理算法

移动非垃圾对象时，会利用写屏障同步修改引用地址。



#### 标记-清除算法





### 垃圾收集器Serial&Parallel&ParNew&CMS



#### Serial收集器(-XX:+UseSerialGC -XX:+UseSerialOldGC)

- 简介

  “单线程”串行垃圾收集器，GC时会停止工作线程，STW。

- 优缺点

  优点：比较简单，在单核cpu执行效率高。

  缺点：单线程标记，无法充分利用多核cpu，浪费资源，在多核cpu情况下，效率与并行垃圾收集器相比太低。

- 算法

  新生代：标记-复制算法

  老年代：标记-整理算法

#### Parallel Scavenge收集器(-XX:+UseParallelGC(年轻代)，-XX:+UseParallelOldGC(老年代))

- 简介

  关注点：吞吐量， Jdk1.8默认垃圾收集器

- 优缺点

  优点：

  缺点：STW时间较长，尤其是在大内存情况下。

- 适用内存

  4G小内存

- 算法

  新生代：复制算法

  老年代：标记-整理算法

  







#### ParNew收集器(-XX:+UseParNewGC(年轻代))



- 算法

  新生代：复制算法

#### CMS垃圾收集器(-XX:+UseConcMarkSweepGC(老年代))

- 简介

  关注点：用户体验，相比ParallelOld垃圾收集器，减少整个GC STW时间。

- 适用内存

  较大内存，8G左右

- GC步骤

  1. 初始标记

     STW，单线程标记GC Root直接引用的对象，也可以通过设置参数-XX:+CMSParallellnitialMarkEnabled启用多线程标记  ，速度很快。

  2. 并发标记

     占用时间最多，大概80%，用户工作线程与GC线程并行执行。

  3. 重新标记

     STW，多线程重新标记。

     三色标记法增量更新算法做重新标记。

  4. 并发清理

  5. 并发重置

- 优缺点

  优点：

  缺点：

  - 对cpu资源敏感，因为会和用户工作线程争抢资源；
  - 无法处理**浮动垃圾**，在并发标记、并发清理阶段又产生新的垃圾对象，只能等待下次GC才能回收。
  - 使用“标记-清除”算法会导致GC结束时会有大量内存空间碎片产生，但可以通过-XX:+UseCMSCompactAtFullCollection可以让JVM标记-清除之后再整理内存碎片；
  - “concurrent mode failure”，此时会进入STW，用Serial old垃圾收集器来单线程GC；

- CMS核心参数设置：

  1. -XX:+UseConcMarkSweepGC：启用CMS垃圾收集器；
  2. -XX:ConcGCThreads：并发的GC线程数；
  3. ss
  4. ss
  5. -XX:CMSInitiatingOccupancyFraction：当老年代使用达到该比例会触发Full GC，（默认92，百分比），这个参数就是为了避免“concurrent mode failure”出现，当系统中大对象比较多时、并发高时，该参数值应适当减小；

  

### 设置合适的垃圾收集器





### 垃圾收集底层三色标记算法实现原理



#### 写屏障

JVM在赋值操作前后进行一些操作，类似于AOP，不同于内存屏障，这是在代码级别的操作。

### CMS并发清理阶段会误删新生对象吗



### 记忆集与卡表

为了解决跨代引用

卡表是记忆集的实现方式

