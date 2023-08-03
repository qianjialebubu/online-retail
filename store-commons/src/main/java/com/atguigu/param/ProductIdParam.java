package com.atguigu.param;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author qjl
 * @create 2023-02-02 23:59
 */
@Data
public class ProductIdParam {
    @NotNull
    private Integer productID;
}
