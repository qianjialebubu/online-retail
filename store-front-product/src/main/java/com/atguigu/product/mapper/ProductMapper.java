package com.atguigu.product.mapper;

import com.atguigu.pojo.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author qjl
 * @create 2023-02-01 19:54
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {
}
