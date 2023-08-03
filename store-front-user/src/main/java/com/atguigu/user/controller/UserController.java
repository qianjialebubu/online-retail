package com.atguigu.user.controller;

import com.atguigu.param.PageParam;
import com.atguigu.param.UserCheckParam;
import com.atguigu.param.UserLoginParam;
import com.atguigu.pojo.User;
import com.atguigu.user.service.UserService;
import com.atguigu.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author qjl
 * @create 2023-01-31 15:38
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("check")
    public R check(@RequestBody @Validated UserCheckParam userCheckParam, BindingResult result){
        boolean b = result.hasErrors();
        if(b){
            return  R.fail("账号为null,不可使用");
        }
        return userService.check(userCheckParam);
    }

    @PostMapping("register")
    public R register(@RequestBody @Validated User user, BindingResult result){
        if(result.hasErrors()){
            return R.fail("参数异常，不可注册");
        }
        return userService.register(user);
    }

    @PostMapping("login")
    public R login(@RequestBody @Validated UserLoginParam userLoginParam, BindingResult result){
        if (result.hasErrors()){
            return R.fail("参数错误，不可登录");
        }
        return userService.login(userLoginParam);
    }
    /**
     * 后台管理调用
     * @param pageParam
     * @return
     */
    @PostMapping("/list")
    public Object listPage(@RequestBody PageParam pageParam){

        return userService.listPage(pageParam);
    }

    /**
     * 后台管理调用,删除用户数据
     * @param userId
     * @return
     */
    @PostMapping("/remove")
    public Object remove(@RequestBody Integer userId){

        return userService.remove(userId);
    }
    /**
     * 后台管理调用,修改用户数据
     * @param user
     * @return
     */
    @PostMapping("/update")
    public  Object update(@RequestBody User user){

        return userService.update(user);
    }


}
