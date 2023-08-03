package com.atguigu.admin.service;

import com.atguigu.param.ProductParamsSearch;
import com.atguigu.param.ProductSaveParam;
import com.atguigu.pojo.Product;

/**
 * @author qjl
 * @create 2023-02-05 0:46
 */
public interface ProductService {
    Object list(ProductParamsSearch productParamsSearch);

    Object save(ProductSaveParam saveParam);

    Object update(Product product);
}
