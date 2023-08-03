package com.atguigu.admin.service;

import com.atguigu.param.PageParam;
import com.atguigu.pojo.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author qjl
 * @create 2023-02-05 10:19
 */
public interface OrderService{
    Object list(PageParam pageParam);
}
