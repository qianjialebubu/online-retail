package com.atguigu.carousel.controller;

import com.atguigu.carousel.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qjl
 * @create 2023-02-01 15:54
 */
@RestController
@RequestMapping(value = "/carousel")
public class CarouselController {
    @Autowired
    private CarouselService carouselService;
    @PostMapping("list")
    public Object list(){
        return carouselService.list();
    }
}
