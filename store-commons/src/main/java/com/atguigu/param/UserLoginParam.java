package com.atguigu.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author qjl
 * @create 2023-02-01 8:50
 * 用户登录的接口封装类
 */
@Data
public class UserLoginParam {
    @NotBlank
    private String userName;
    @NotBlank
    private String password;
}
