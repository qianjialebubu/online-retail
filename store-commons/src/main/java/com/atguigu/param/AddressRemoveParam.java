package com.atguigu.param;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author qjl
 * @create 2023-02-01 9:42
 */
@Data
public class AddressRemoveParam {
    @NotNull
    private Integer id;
}
