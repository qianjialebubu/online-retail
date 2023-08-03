package com.atguigu.user.mapper;

import com.atguigu.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author qjl
 * @create 2023-01-31 15:47
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}

