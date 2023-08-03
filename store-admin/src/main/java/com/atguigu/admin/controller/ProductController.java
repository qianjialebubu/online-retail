package com.atguigu.admin.controller;

import com.atguigu.admin.service.ProductService;
import com.atguigu.admin.utils.AliyunOSSUtils;
import com.atguigu.param.ProductParamsSearch;
import com.atguigu.param.ProductSaveParam;
import com.atguigu.pojo.Product;
import com.atguigu.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author qjl
 * @create 2023-02-05 0:46
 */
@RestController
@RequestMapping("product")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private AliyunOSSUtils aliyunOSSUtils;

    @GetMapping("list")
    public Object list(ProductParamsSearch productParamsSearch){
        return productService.list(productParamsSearch);
    }

    @PostMapping("upload")
    public Object upload(MultipartFile img) throws Exception {

        String filename = img.getOriginalFilename();
        String contentType = img.getContentType();
        long millis = System.currentTimeMillis();

        filename = millis + filename; //防止重复

        String url = aliyunOSSUtils.uploadImage(filename, img.getBytes(), contentType, 1000);
        System.out.println("url = " + url);
        return R.ok("上传成功",url);

    }

    /**
     * 商品信息保存
     * @param saveParam
     * @return
     */
    @PostMapping("save")
    public Object save(ProductSaveParam saveParam){
        return productService.save(saveParam);
    }

    /**
     * 修改商品信息
     * @param product
     * @return
     */
    @PostMapping("update")
    public Object update(Product product){

        return productService.update(product);
    }
}
