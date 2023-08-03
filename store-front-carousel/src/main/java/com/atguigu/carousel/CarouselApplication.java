package com.atguigu.carousel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author qjl
 * @create 2023-02-01 15:49
 */
@SpringBootApplication
@MapperScan(basePackages = "com.atguigu.carousel.mapper")
@EnableCaching
public class CarouselApplication {
    public static void main(String[] args) {
        SpringApplication.run(CarouselApplication.class, args);
    }
}
