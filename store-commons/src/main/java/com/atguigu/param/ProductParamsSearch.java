package com.atguigu.param;

import lombok.Data;

/**
 * @author qjl
 * @create 2023-02-03 16:10
 */
@Data
public class ProductParamsSearch {

    private String search;
    private int    currentPage = 1;
    private int    pageSize = 15;

    /**
     * 运算分页起始值
     * @return
     */
    public int getFrom(){
        return (currentPage-1)*pageSize;
    }

    /**
     * 返回查询值
     * @return
     */
    public int getSize(){
        return pageSize;
    }
}
