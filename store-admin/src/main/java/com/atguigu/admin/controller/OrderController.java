package com.atguigu.admin.controller;

import com.atguigu.admin.service.OrderService;
import com.atguigu.param.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qjl
 * @create 2023-02-05 10:19
 */
@RestController
@RequestMapping("order")
public class OrderController
{
    @Autowired
    private OrderService orderService;

    @GetMapping("list")
    public Object list(PageParam pageParam){

        return orderService.list(pageParam);
    }

}

