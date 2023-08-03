package com.atguigu.product.controller;

import com.atguigu.param.*;
import com.atguigu.pojo.Product;
import com.atguigu.product.service.ProductService;
import com.atguigu.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author qjl
 * @create 2023-02-01 19:51
 */
@Slf4j
@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;
    //查询单类商品

    @PostMapping("/promo")
    public R promo(@RequestBody @Validated ProductPromoParam productPromoParam, BindingResult result){

        if (result.hasErrors()){
            return R.fail("数据查询失败!");
        }
        return  productService.promo(productPromoParam.getCategoryName());
    }
    //查询热门商品
    @PostMapping("/hots")
    public R hots(@RequestBody @Validated ProductHotParam productHotParam,BindingResult result){
        if (result.hasErrors()){
            return R.fail("数据查询失败!");
        }
        return productService.hot(productHotParam);
    }

    @PostMapping("/category/list")
    public R clist(){
        return productService.clist();
    }
    /**
     * 类别查询
     * @param productParamInteger
     * @return
     */
    @PostMapping("bycategory")
    public Object byCategory(@RequestBody ProductParamInteger productParamInteger){

        return productService.byCategory(productParamInteger);
    }
    /**
     * 查询全部商品,可以复用业务!
     * @param productParamInteger
     * @return
     */
    @PostMapping("all")
    public Object all(@RequestBody ProductParamInteger productParamInteger){

        return productService.all(productParamInteger);
    }

    @PostMapping("detail")
//    public Object detail(@RequestBody Integer param){
    public R detail(@RequestBody @Validated ProductIdParam productIdParam){
        Integer productID = productIdParam.getProductID();
//        Integer productID =param;
        return (R) productService.detail(productID);
    }
    @PostMapping("/pictures")
    public R productPictures(@RequestBody ProductIdParam productIdParam){
        return productService.pictures(productIdParam.getProductID());
    }
    @PostMapping("/search")
    public R search(@RequestBody ProductParamsSearch productParamsSearch){
        return productService.search(productParamsSearch);
    }
    @PostMapping("/searchPage")
    R searchPage(@RequestBody ProductParamsSearch paramsSearch){
        log.info("SearchingPage,paramsSearch={}",paramsSearch);
        return productService.search(paramsSearch);
    }

    /**
     * 供收藏服务使用,根据传入的id,查询商品集合!
     * @return
     */
    @PostMapping("ids")
    public List<Product> list(@RequestBody ProductIdsParam productIdsParam){

        return productService.ids(productIdsParam);
    }
    /**
     * 类别服务调用管理调用
     */
    @PostMapping("/category/count")
    public Long categoryCount(@RequestBody Integer categoryId){

        return productService.categoryCount(categoryId);
    }
    @PostMapping("save")
    public R save(@RequestBody ProductSaveParam productSaveParam){
        return productService.save(productSaveParam);
    }
    @PostMapping("update")
    public R update(@RequestBody Product product){
        return productService.update(product);
    }

}

