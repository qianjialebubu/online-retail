package com.atguigu.order.controller;

import com.atguigu.order.service.OrderService;
import com.atguigu.param.CartListParam;
import com.atguigu.param.CartParam;
import com.atguigu.param.OrderParam;
import com.atguigu.param.PageParam;
import com.atguigu.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qjl
 * @create 2023-02-04 12:21
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 订单数据保存
     * @param orderParam
     * @return
     */
    @PostMapping("save")
    public R save(@RequestBody OrderParam orderParam){


        return orderService.save(orderParam);
    }
    /**
     * 订单集合查询,注意,按照类别查询!
     * @param cartListParam
     * @return
     */
    @PostMapping("/list")
    public R list(@RequestBody @Validated  CartListParam cartListParam , BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return R.fail("参数错误");
        }
        return orderService.list(cartListParam.getUserId());
    }

    @PostMapping("/admin/list")
    public Object adminList(@RequestBody PageParam pageParam){

        return orderService.adminList(pageParam);
    }
}
