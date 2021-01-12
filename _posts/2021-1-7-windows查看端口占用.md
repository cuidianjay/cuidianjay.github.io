---
title: Windows查看端口占用
date: 2021-1-7 15:50:20 +0800
categories: [Windows, 命令]
tags: [windows命令, 端口占用]
math: true
image: 
---

```shell
#查找指定端口占用进程
netstat -ano|findstr "4000"

#打印结果：
TCP    127.0.0.1:4000         0.0.0.0:0              LISTENING       9512
TCP    127.0.0.1:4000         127.0.0.1:11561        TIME_WAIT       0
TCP    127.0.0.1:4000         127.0.0.1:11569        TIME_WAIT       0
TCP    127.0.0.1:4000         127.0.0.1:11643        TIME_WAIT       0
TCP    127.0.0.1:4000         127.0.0.1:11644        TIME_WAIT       0
TCP    127.0.0.1:4000         127.0.0.1:11674        TIME_WAIT       0
TCP    127.0.0.1:4000         127.0.0.1:11676        TIME_WAIT       0
TCP    127.0.0.1:11560        127.0.0.1:4000         TIME_WAIT       0

#强制杀掉进程
taskkill /f /pid 9521
```



