package com.atguigu.admin.service;

import com.atguigu.param.AdminUserParam;
import com.atguigu.pojo.AdminUser;
import com.atguigu.utils.R;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author qjl
 * @create 2023-02-04 22:13
 */
public interface AdminUserService extends IService<AdminUser> {
    R login(AdminUserParam adminUserParam);
}
