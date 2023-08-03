package com.atguigu.user.service.impl;

import com.atguigu.pojo.Address;
import com.atguigu.user.mapper.AddressMapper;
import com.atguigu.user.service.AddressService;
import com.atguigu.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qjl
 * @create 2023-02-01 9:23
 */
@Service
@Slf4j
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressMapper addressMapper;
    @Override
    public R list(Integer userId) {
        QueryWrapper<Address> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        List<Address> addressList = addressMapper.selectList(wrapper);
        R ok = R.ok("查询成功", addressList);
        log.info("AddressServiceImpl.list业务结束，结果:{}",ok);
        return ok;

    }

    /**
     * 实现对地址的新增操作，插入地址数据,插入成功以后,要返回新的数据集合!
     * @param address
     * @return
     */
    @Override
    public R save(Address address) {
        int i = addressMapper.insert(address);
        if(i==0){
            log.info("AddressServiceImpl.save业务结束，结果:{}","地址失败!");
            return R.fail("插入地址失败!");
        }
        return list(address.getUserId());
    }

    @Override
    public R remove(Integer id) {
        int byId = addressMapper.deleteById(id);
        if(byId==0){
            log.info("AddressServiceImpl.remove业务结束，结果:{}","地址删除失败");
            return R.fail("删除地址数据失败!");
        }
        log.info("AddressServiceImpl.remove业务结束，结果:{}","地址删除成功!");

        return R.ok("地址删除成功!");
    }
}
