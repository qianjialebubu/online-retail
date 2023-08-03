package com.atguigu.admin.service.impl;

import com.atguigu.admin.service.ProductService;
import com.atguigu.clients.ProductClient;
import com.atguigu.param.ProductParamsSearch;
import com.atguigu.param.ProductSaveParam;
import com.atguigu.pojo.Product;
import com.atguigu.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

/**
 * @author qjl
 * @create 2023-02-05 0:47
 */
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductClient productClient;

    /**
     * 商品分页,关键字分页查询!
     * @param productParamsSearch
     * @return
     */
    @Override
    public Object list(ProductParamsSearch productParamsSearch) {
        R r = productClient.searchPage(productParamsSearch);
        log.info("ProductServiceImpl.list业务结束，结果:{}",r);
        return r;
    }

    /**
     * 保存商品业务!
     * 1.保存商品
     * 2.保存商品图片
     * 3.商品缓存数据处理 [注解]
     * 4.查询缓存es处理 [异步]
     *
     * @param saveParam
     * @return
     */
    @CacheEvict(value = "list.product", allEntries = true)
    @Override
    public Object save(ProductSaveParam saveParam) {
        log.info("saving product-----={}", saveParam.getProductPrice().toString());
        log.info("saving product={}", saveParam.toString());

        //保存 商品和商品图片
        R r = productClient.save(saveParam);
        log.info("ProductServiceImpl.save业务结束，结果:{}",r);
        return r;
    }

    /**
     * 修改商品信息
     * 1.修改商品信息
     * 2.清空商品缓存集合
     * 3.更新缓存es处理 [异步]
     *
     * @param product
     * @return
     */
    @CacheEvict(value = "list.product",allEntries = true)
    @CachePut(value = "product",key = "#product.productId")
    @Override
    public Object update(Product product) {

//        R r = productClient.update(product);
//        log.info("ProductServiceImpl.update业务结束，结果:{}",r);
//        return r;
        return null;
    }
}
