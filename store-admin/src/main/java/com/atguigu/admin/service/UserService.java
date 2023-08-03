package com.atguigu.admin.service;

import com.atguigu.param.PageParam;
import com.atguigu.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author qjl
 * @create 2023-02-04 22:56
 */
@Mapper
public interface UserService {
    Object listPage(PageParam pageParam);

    Object remove(Integer userId);

    Object update(User user);

    Object save(User user);
}
