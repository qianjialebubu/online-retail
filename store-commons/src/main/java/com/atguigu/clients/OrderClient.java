package com.atguigu.clients;

import com.atguigu.param.PageParam;
import com.atguigu.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author qjl
 * @create 2023-02-05 10:13
 */
@FeignClient("order-service")
public interface OrderClient {
    @PostMapping("order/admin/list")
    R adminList(@RequestBody PageParam pageParam);
}
