---
title: linux开启关闭ping
date: 2020-12-23 17:07:37 +0800
categories: [linux, 系统设置]
tags: [linux, ping]
math: true
image: 
---

### 内核层面设置ping

~~~shell
#修改/etc/sysctl.conf文件，新增
net.ipv4.icmp_echo_ignore_all=1 #（0：允许，1：禁止）
sysctl -p #使配置修改生效
~~~

### 防火墙设置

~~~shell
#防火墙开启ping必须要求内核设置允许ping
#允许ping
iptables -A INPUT -p icmp --icmp-type echo-request -j ACCEPT
iptables -A OUTPUT -p icmp --icmp-type echo-reply -j ACCEPT
#禁止ping
iptables -A INPUT -p icmp --icmp-type 8 -s 0/0 -j DROP
      --icmp-type 8 echo request  表示回显请求（ping请求）
      0/0 表示所有 IP
~~~



