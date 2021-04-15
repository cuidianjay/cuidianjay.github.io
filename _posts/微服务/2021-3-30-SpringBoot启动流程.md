---
title: SpringBoot启动流程
date: 2021-3-30 10:06:43 +0800
categories: [微服务, SpringBoot]
tags: [微服务, SpringBoot, 启动流程]
math: true
image: 
---



### 为什么SpringBoot项目打的JAR包可以直接运行？

通过spring-boot-maven-plugin Maven插件可以把项目依赖的jar包和资源打包成一个Fat Jar，以及生成MANIFEST.MF文件，MF文件中指定Main-Class为JarLauncher，当运行java -jar xxx.jar命令时，JarLauncher就会加载BOOT-INF/classes目录中的class及BOOT-INF/lib目录下的jar文件，然后通过反射的方式，调用Start-Class指定的SpringBoot启动类的main方法，实现了fat jar的启动。