package com.atguigu.collect;

import com.atguigu.clients.ProductClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author qjl
 * @create 2023-02-04 0:06
 */
@MapperScan(basePackages = "com.atguigu.collect.mapper")
@SpringBootApplication
@EnableFeignClients(clients = {ProductClient.class})
public class CollectApplication {

    public static void main(String[] args) {
        SpringApplication.run(CollectApplication.class,args);
    }
}
