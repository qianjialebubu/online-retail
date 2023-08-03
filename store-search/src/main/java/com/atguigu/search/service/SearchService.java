package com.atguigu.search.service;


import com.atguigu.param.ProductParamsSearch;
import com.atguigu.pojo.Product;
import com.atguigu.utils.R;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

/**
 * @author qjl
 * @create 2023-02-03 16:12
 */

public interface SearchService {
    R search(ProductParamsSearch productParamsSearch) throws JsonProcessingException;

    R save(Product product) throws IOException;

    R remove(Integer productId) throws IOException;
}
