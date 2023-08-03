package com.atguigu.order.service;

import com.atguigu.param.OrderParam;
import com.atguigu.param.PageParam;
import com.atguigu.pojo.Order;
import com.atguigu.utils.R;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author qjl
 * @create 2023-02-04 12:29
 */
public interface OrderService extends IService<Order> {
    R save(OrderParam orderParam);

    R list(Integer userId);

    Object adminList(PageParam pageParam);
}
