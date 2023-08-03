package com.atguigu.admin.service;

import com.atguigu.param.PageParam;
import com.atguigu.pojo.Category;

/**
 * @author qjl
 * @create 2023-02-05 0:02
 */
public interface CategoryService {
    Object listPage(PageParam pageParam);

    Object save(Category category);

    Object remove(Integer categoryId);

    Object update(Category category);
}
