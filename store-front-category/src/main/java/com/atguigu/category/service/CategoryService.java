package com.atguigu.category.service;

import com.atguigu.param.PageParam;
import com.atguigu.param.ProductHotParam;
import com.atguigu.pojo.Category;
import com.atguigu.utils.R;

/**
 * @author qjl
 * @create 2023-02-01 19:33
 */
public interface CategoryService {
    R byName(String categoryName);
    R list();

    R hotsCategory(ProductHotParam productHotParam);

    R page(PageParam pageParam);

    R save(Category category);

    R remove(Integer categoryId);

    R update(Category category);
}
