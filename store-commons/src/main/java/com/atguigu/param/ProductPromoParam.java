package com.atguigu.param;

/**
 * @author qjl
 * @create 2023-02-01 17:47
 */

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * projectName: b2c-store
 * <p>
 * description: 类别热门商品参数接收
 */
@Data
public class ProductPromoParam {

    @NotBlank
    private String categoryName;
}
