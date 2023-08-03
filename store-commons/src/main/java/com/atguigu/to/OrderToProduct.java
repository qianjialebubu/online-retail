package com.atguigu.to;

/**
 * @author qjl
 * @create 2023-02-04 1:23
 */
//调用product参数

import lombok.Data;

import java.io.Serializable;


@Data
public class OrderToProduct implements Serializable {
    public static final Long serialVersionUID = 1L;

    //商品id
    private Integer productId;
    //购买数量
    private Integer num;
}
