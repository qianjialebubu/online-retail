package com.atguigu.user.controller;

import com.atguigu.param.AddressListParam;
import com.atguigu.param.AddressParam;
import com.atguigu.param.AddressRemoveParam;
import com.atguigu.pojo.Address;
import com.atguigu.user.service.AddressService;
import com.atguigu.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qjl
 * @create 2023-02-01 9:18
 */
@RestController
@RequestMapping("/user/address")
public class AddressController {
    @Autowired
    private AddressService addressService;
    @PostMapping("list")
    public R list(@RequestBody @Validated AddressListParam addressListParam, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return R.fail("参数异常,查询失败!");
        }
        return addressService.list(addressListParam.getUserId());
    }
    @PostMapping("save")
    public R save(@RequestBody @Validated AddressParam addressParam, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return R.fail("参数异常，查询失败！");
        }
        Address address = addressParam.getAdd();
        address.setUserId(addressParam.getUserId());
        return addressService.save(address);
    }
    @PostMapping("remove")
    public R remove(@RequestBody @Validated AddressRemoveParam addressRemoveParam,BindingResult result){
        if (result.hasErrors()){
            return R.fail("参数异常，查询失败！");
        }
        return addressService.remove(addressRemoveParam.getId());
    }
}
