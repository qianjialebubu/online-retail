package com.atguigu.category.controller;

import com.atguigu.category.service.CategoryService;
import com.atguigu.param.PageParam;
import com.atguigu.param.ProductHotParam;
import com.atguigu.pojo.Category;
import com.atguigu.utils.R;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author qjl
 * @create 2023-02-01 18:54
 */
@RestController
@RequestMapping(value = "/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping(value = "/promo/{categoryName}")
    public R byName(@PathVariable String categoryName) {
        if(StringUtils.isEmpty(categoryName)){
            return R.fail("类别名称为null,无法查询类别数据!");
        }
        return categoryService.byName(categoryName);
    }
    @GetMapping(value = "list")
    public R list(){
        return categoryService.list();
    }
    @PostMapping("hots")
    //查询热门商品，且根据前端返回的数据进行数据库查询，返回id再根据id进行查询
    public R hotsCategory(@RequestBody @Validated ProductHotParam productHotParam, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return R.fail("类别集合查询失败");
        }
        return categoryService.hotsCategory(productHotParam);
    }

    /**
     * 后台管理调用服务
     * @param pageParam
     * @return
     */
    @PostMapping("admin/list")
    public R pageList(@RequestBody PageParam pageParam){
        return categoryService.page(pageParam);
    }
    @PostMapping("admin/save")
    public R save(@RequestBody Category category){
        return categoryService.save(category);
    }
    @PostMapping("admin/remove")
    public R remove(@RequestBody Integer categoryId){
        return categoryService.remove(categoryId);
    }
    @PostMapping("admin/update")
    public R update(@RequestBody Category category){

        return categoryService.update(category);
    }
}
