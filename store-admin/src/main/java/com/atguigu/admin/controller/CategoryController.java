package com.atguigu.admin.controller;

import com.atguigu.admin.service.CategoryService;
import com.atguigu.param.PageParam;
import com.atguigu.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qjl
 * @create 2023-02-05 0:01
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public Object list(PageParam pageParam){

        return  categoryService.listPage(pageParam);
    }
    @PostMapping("/save")
    public Object save(Category category){

        return categoryService.save(category);
    }
    @PostMapping("/remove")
    public Object remove(Integer categoryId){

        return categoryService.remove(categoryId);
    }
    @PostMapping("/update")
    public Object update(Category category){

        return categoryService.update(category);
    }
}
