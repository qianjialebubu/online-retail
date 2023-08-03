package com.atguigu.param;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author qjl
 * @create 2023-02-04 0:31
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CartParam {

    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("product_id")
    private Integer productId;
    private Integer num;
}

