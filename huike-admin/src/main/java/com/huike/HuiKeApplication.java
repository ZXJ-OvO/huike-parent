package com.huike;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 启动程序
 *
 * @author zxj
 */
@MapperScan("com.huike.mapper") // 指定要扫描的Mapper类的包的路径
@EnableAspectJAutoProxy(exposeProxy = true) // 表示通过aop框架暴露该代理对象,AopContext能够访问
@ServletComponentScan //开启servlet的组件扫描
@EnableScheduling //开启定时任务
@SpringBootApplication
public class HuiKeApplication {

    public static void main(String[] args) {
        SpringApplication.run(HuiKeApplication.class, args);
    }

}
