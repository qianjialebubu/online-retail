package com.atguigu.user.service;

import com.atguigu.pojo.Address;
import com.atguigu.utils.R;

/**
 * @author qjl
 * @create 2023-02-01 9:22
 */
public interface AddressService {
    R list(Integer userId);

    R save(Address address);

    R remove(Integer id);
}
