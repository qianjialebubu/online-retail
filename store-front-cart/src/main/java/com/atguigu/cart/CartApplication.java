package com.atguigu.cart;

import com.atguigu.clients.ProductClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author qjl
 * @create 2023-02-04 0:29
 */
@MapperScan(basePackages = "com.atguigu.cart.mapper")
@SpringBootApplication
@EnableFeignClients(clients = ProductClient.class)
public class CartApplication {

    public static void main(String[] args) {
        SpringApplication.run(CartApplication.class,args);
    }
}
