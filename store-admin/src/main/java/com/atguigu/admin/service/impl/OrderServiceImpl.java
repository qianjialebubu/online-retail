package com.atguigu.admin.service.impl;

import com.atguigu.admin.service.OrderService;
import com.atguigu.clients.OrderClient;
import com.atguigu.param.PageParam;
import com.atguigu.pojo.Order;
import com.atguigu.utils.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author qjl
 * @create 2023-02-05 10:20
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderClient orderClient;

    /**
     * 分页查询订单数据
     *
     * @param pageParam
     * @return
     */
    @Override
    public Object list(PageParam pageParam) {

        R r = orderClient.adminList(pageParam);

        log.info("OrderServiceImpl.list业务结束，结果:{}",r);

        return r;
    }
}
