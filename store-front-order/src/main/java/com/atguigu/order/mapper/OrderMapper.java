package com.atguigu.order.mapper;

import com.atguigu.pojo.Order;
import com.atguigu.vo.AdminOrderVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author qjl
 * @create 2023-02-04 12:32
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
    List<AdminOrderVo> selectAdminOrders(@Param("offset") int offset, @Param("number")int number);
}
