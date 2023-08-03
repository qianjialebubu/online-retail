package com.atguigu.product.service;

import com.atguigu.param.*;
import com.atguigu.pojo.Product;
import com.atguigu.to.OrderToProduct;
import com.atguigu.utils.R;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author qjl
 * @create 2023-02-01 19:52
 */
public interface ProductService extends IService<Product> {
    R promo(String categoryName);

    R hot(ProductHotParam productHotParam);

    R clist();

    Object byCategory(ProductParamInteger productParamInteger);

    Object all(ProductParamInteger productParamInteger);

    Object detail(Integer productID);

    R pictures(Integer productID);

    List<Product> list();

    R search(ProductParamsSearch productParamsSearch);

    List<Product> ids(ProductIdsParam productIdsParam);

    void subNumber(List<OrderToProduct> orderToProducts);

    Long categoryCount(Integer categoryId);

    R save(ProductSaveParam productSaveParam);

    R update(Product product);

}
