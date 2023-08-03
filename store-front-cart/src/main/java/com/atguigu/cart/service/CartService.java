package com.atguigu.cart.service;

import com.atguigu.param.CartParam;
import com.atguigu.utils.R;

import java.util.List;

/**
 * @author qjl
 * @create 2023-02-04 0:33
 */
public interface CartService {
    R save(CartParam cartParam);

    R list(CartParam cartParam);

    R update(CartParam cartParam);

    R remove(CartParam cartParam);

    /*清空对应id购物车

     */
    void clearIds(List<Integer> cartIds);
}
