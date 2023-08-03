package com.atguigu.param;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author qjl
 * @create 2023-02-04 0:09
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CollectParam {

    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("product_id")
    private Integer productId;
}
