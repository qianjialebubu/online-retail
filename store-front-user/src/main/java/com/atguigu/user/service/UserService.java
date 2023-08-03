package com.atguigu.user.service;

import com.atguigu.param.PageParam;
import com.atguigu.param.UserCheckParam;
import com.atguigu.param.UserLoginParam;
import com.atguigu.pojo.User;
import com.atguigu.utils.R;

/**
 * @author qjl
 * @create 2023-01-31 15:55
 * @// TODO: 2023/1/31  
 */
public interface UserService {
    R check(UserCheckParam userCheckParam);

    R register(User user);

    R login(UserLoginParam userLoginParam);

    Object listPage(PageParam pageParam);

    Object remove(Integer userId);

    Object update(User user);
}
