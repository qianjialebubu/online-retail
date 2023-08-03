package com.atguigu.clients;

import com.atguigu.param.ProductIdParam;
import com.atguigu.param.ProductIdsParam;
import com.atguigu.param.ProductParamsSearch;
import com.atguigu.param.ProductSaveParam;
import com.atguigu.pojo.Product;
import com.atguigu.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author qjl
 * @create 2023-02-03 11:32
 */
@FeignClient(value = "product-service")
public interface ProductClient {
    @GetMapping("/product/list")
    List<Product> list();

    /**
     * 收藏模块调用
     * @param productIdsParam
     * @return
     */
    @PostMapping("/product/ids")
    List<Product> ids(@RequestBody ProductIdsParam productIdsParam);

    @PostMapping("/product/cart/detail")
    Product productDetail(@RequestBody ProductIdParam productIdParam);
    @PostMapping("/product/category/count")
    long count(@RequestBody  Integer categoryId);
//    @PostMapping("/product/cart/list")
//    List<Product> cartList(@RequestBody ProductCollect)
    /**
     * 后台管理调用!
     * @param paramsSearch
     * @return
     */
    @PostMapping("/product/search")
    R searchPage(@RequestBody ProductParamsSearch paramsSearch);

    @PostMapping("/product/save")
    R save(@RequestBody ProductSaveParam saveParam);

    @PostMapping("/product/admin/count")
    R update(@RequestBody  Integer categoryId);

    @PostMapping("/product/admin/save")
    R update(@RequestBody  ProductSaveParam productSaveParam );

}
