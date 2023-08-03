package com.atguigu.user.service.impl;

import com.atguigu.param.PageParam;
import com.atguigu.user.constants.UserConstants;
import com.atguigu.param.UserCheckParam;
import com.atguigu.param.UserLoginParam;
import com.atguigu.pojo.User;
import com.atguigu.user.mapper.UserMapper;
import com.atguigu.user.service.UserService;
import com.atguigu.utils.MD5Util;
import com.atguigu.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qjl
 * @create 2023-01-31 15:57
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public R check(UserCheckParam userCheckParam) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name",userCheckParam.getUserName());
        Long total = userMapper.selectCount(wrapper);
        if (total == 0) {
            log.info("UserServiceImpl.check业务结束，结果:{}","账号可以使用!");
            return R.ok("账号不存在，可以使用");
        }
        log.info("userService.check() 执行结束，结果:{}","账号不可以使用");
        return R.fail("账号已经存在，不可注册");
    }

    @Override
    public R register(User user) {
       //先做一个检查
        UserCheckParam param = new UserCheckParam();
        param.setUserName(user.getUserName());
        if(check(param).getCode()!="001"){
            log.info("UserServiceImpl.register业务结束，结果:{}","账号存在,注册失败!");
            return R.fail("账号已经存在,不可注册!");
        }
        String newPwd = MD5Util.encode(user.getPassword() + UserConstants.USER_SLAT);
        user.setPassword(newPwd);
        int total = userMapper.insert(user);
        if(total==0){
            log.info("UserServiceImpl.register业务结束，结果:{}","数据插入失败!注册失败!");
            return R.fail("注册失败!请稍后再试!");
        }
        log.info("UserServiceImpl.register业务结束，结果:{}","注册成功!");
        return R.ok("注册成功!");
    }

    /**
     * * 登录业务
     *  *   1. 密码的加密和加盐处理
     *  *   2. 账号和密码进行数据库查询.返回一个完整的数据库user对象
     *  *   3. 判断返回结果
     * @param userLoginParam
     * @return
     */
    /**
     * 登录业务
     *   1. 密码的加密和加盐处理
     *   2. 账号和密码进行数据库查询.返回一个完整的数据库user对象
     *   3. 判断返回结果
     * @param userLoginParam 账号和密码 已经校验 但是密码是明文!
     * @return 结果 001 004
     */
    @Override
    public R login(UserLoginParam userLoginParam) {

        //1.密码处理
        String newPwd = MD5Util.encode(userLoginParam.getPassword() + UserConstants.USER_SLAT);

        //2.数据库查询
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",userLoginParam.getUserName());
        queryWrapper.eq("password",newPwd);

        User user = userMapper.selectOne(queryWrapper);

        //3.结果处理
        if (user == null) {
            log.info("UserServiceImpl.login业务结束，结果:{}","账号和密码错误!");
            return R.fail("账号或者密码错误!");
        }

        log.info("UserServiceImpl.login业务结束，结果:{}","登录成功!");
        //不返回password属性!
        user.setPassword(null);
        return R.ok("登录成功!",user);
    }

    /**
     * 分页数据查询
     *
     * @param pageParam
     * @return
     */
    @Override
    public Object listPage(PageParam pageParam) {

        int currentPage = pageParam.getCurrentPage();
        int pageSize = pageParam.getPageSize();

        //设置分页属性
        IPage<User> page = new Page<>(currentPage,pageSize);
        page = userMapper.selectPage(page, null);

        //结果封装
        long total = page.getTotal();
        List<User> records = page.getRecords();

        R ok = R.ok("查询成功!", records, total);

        log.info("UserServiceImpl.listPage业务结束，结果:{}",ok);

        return ok;
    }

    /**
     * 删除用户数据
     *
     * @param userId
     * @return
     */
    @Override
    public Object remove(Integer userId) {

        int rows = userMapper.deleteById(userId);

        log.info("UserServiceImpl.remove业务结束，结果:{}",rows);

        if (rows > 0){
            return R.ok("删除用户数据成功!");
        }
        return R.fail("删除用户数据失败!");
    }

    /**
     * 修改用户密码
     *
     * @param user
     * @return
     */
    @Override
    public Object update(User user) {

        //检查密码,如果和数据库一致 不需要加密! 证明密码没有修改!
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",user.getUserId());
        queryWrapper.eq("password",user.getPassword());
        Long total = userMapper.selectCount(queryWrapper);

        if (total == 0){
            //密码不同,已经修改! 新密码需要加密
            user.setPassword(MD5Util.encode(user.getPassword()+ com.atguigu.constants.UserConstants.USER_SLAT));
        }

        int rows = userMapper.updateById(user);

        if (rows == 0){
            return R.fail("用户修改失败!");
        }
        return R.fail("用户修改成功");
    }


}
