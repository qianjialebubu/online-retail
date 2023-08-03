package com.atguigu.param;

import lombok.Data;

/**
 * @author qjl
 * @create 2023-02-04 22:41
 */
@Data
public class PageParam {

    private int    currentPage = 1;
    private int    pageSize = 15;
}

