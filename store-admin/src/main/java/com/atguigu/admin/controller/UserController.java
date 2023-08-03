package com.atguigu.admin.controller;

import com.atguigu.admin.service.UserService;
import com.atguigu.param.PageParam;
import com.atguigu.pojo.User;
import com.atguigu.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author qjl
 * @create 2023-02-04 22:56
 */
@Controller
@Slf4j
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @GetMapping("list")
    @ResponseBody

    public Object list(PageParam pageParam){
        log.info(pageParam.toString());
        return userService.listPage(pageParam);
    }
    @PostMapping("remove")
    @ResponseBody
    public Object remove(Integer userId){

        if (userId == null){
            return R.fail("删除失败!");
        }
        return userService.remove(userId);
    }
    @PostMapping("update")
    @ResponseBody
    public Object update(User user){

        return userService.update(user);
    }
    @PostMapping("save")
    @ResponseBody
    public Object save(User user){

        return userService.save(user);
    }



}
