package com.atguigu.collect.service;

import com.atguigu.param.CollectParam;
import com.atguigu.utils.R;

/**
 * @author qjl
 * @create 2023-02-04 0:13
 */
public interface CollectService {
    R save(CollectParam collectParam);

    Object list(CollectParam collectParam);

    Object remove(CollectParam collectParam);
}
