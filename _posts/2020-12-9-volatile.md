---
title: volatile原理
date: 2020-12-9 14:00:49 +0800
categories: [并发编程, java]
tags: [并发编程, 可见性, 有序性]
math: true
image: 
---

## 并发编程三大特性

* 原子性
* 可见性
* 有序性

## volatile作用

volatile可以解决可见性与有序性问题。

### volatile解决可见性问题

volatile变量会被加上Lock汇编指令，Lock指令会触发硬件缓存锁定机制，从而解决可见性问题。硬件缓存锁定机制有总线锁、MESI协议两种实现方式。

### 总线锁

锁定bus总线，当总线被加锁，多核cpu只能已单核的形式去访问内存，效率低下。

### MESI

MESI锁定是的变量所在的缓存行（cache line），从而实现对该变量的锁定。一个缓存行大小是64byte，当变量的大小超过64byte时，变量存放在两个缓存行中，MESI会升级为总线锁，因为MESI不能同时对两个缓存行加锁，cpu读取缓存最小单位为一个缓存行，也就是说操作一个缓存行是原子的。

详细MESI协议信息请看[这里](/posts/缓存一致性协议MESI)

## volatile变量表现形式

volatile变量可以从以下三个层面分析：

### 代码层面

用java修饰符volatile修饰变量。

### 表现形式层面

在线程间该变量是可见的。

### 汇编指令层面

volatile修饰的变量会被加上Lock汇编指令，Lock指令会触发硬件缓存锁定机制，硬件缓存锁定机制有总线锁、MESI协议两种实现方式。

## volatile解决有序性问题



