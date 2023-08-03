package com.atguigu.param;

import lombok.Data;

import java.util.List;

/**
 * @author qjl
 * @create 2023-02-02 23:40
 */
@Data
public class ProductParamInteger {

    private List<Integer> categoryID;
    private int currentPage = 1; //默认值
    private int pageSize = 15; //默认值

}
