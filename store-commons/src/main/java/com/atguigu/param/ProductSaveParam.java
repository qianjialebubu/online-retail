package com.atguigu.param;

import com.atguigu.pojo.Product;
import lombok.Data;

/**
 * @author qjl
 * @create 2023-02-05 13:59
 */
@Data
public class ProductSaveParam extends Product {

    //商品详情图片地址, 多图片地址使用 + 号拼接
    private String pictures;
}