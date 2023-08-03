package com.atguigu.param;

import com.atguigu.pojo.Address;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author qjl
 * @create 2023-02-04 14:36
 */
@Data
public class AddressParam {
    @NotNull
    @JsonProperty("user_id")
    private Integer userId;
    private Address add;
}
