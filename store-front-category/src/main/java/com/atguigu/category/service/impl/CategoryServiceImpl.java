package com.atguigu.category.service.impl;

import com.atguigu.category.mapper.CategoryMapper;
import com.atguigu.category.service.CategoryService;
import com.atguigu.clients.ProductClient;
import com.atguigu.param.PageParam;
import com.atguigu.param.ProductHotParam;
import com.atguigu.pojo.Category;
import com.atguigu.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qjl
 * @create 2023-02-01 19:34
 */
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ProductClient productClient;
    @Override
    public R byName(String categoryName) {
        //封装查询参数
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        wrapper.eq("category_name", categoryName);
        //查询数据库
        Category category = categoryMapper.selectOne(wrapper);
        //结果封装
        if(category==null){
            log.info("CategoryServiceImpl.byName业务结束，结果:类别查询失败");
            return R.fail("查询类别失败");
        }
        log.info("CategoryServiceImpl.byName业务结束，结果:{}","类别查询成功");
        log.info(categoryName);
        return R.ok("查询类别成功",category);
    }

    @Override
    public R list() {
        List<Category> categories = categoryMapper.selectList(null);
        if(categories==null){
            R ok = R.fail("类别全部数据查询为空!");
            log.info("CategoryServiceImpl.list业务结束，结果为空");
        }
        R ok = R.ok("类别全部数据查询成功!", categories);
        log.info("CategoryServiceImpl.list业务结束，结果:{}",ok);
        return ok;
    }

    /**
     * 根据category_name 返回 category_id
     * @param productHotParam
     * @return
     */
    @Override
    public R hotsCategory(ProductHotParam productHotParam) {
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        wrapper.in("category_name",productHotParam.getCategoryName());
        wrapper.select("category_id");
        List<Object> ids = categoryMapper.selectObjs(wrapper);
        R r = R.ok("类别集合查询成功", ids);
        log.info("CategoryServiceImpl.hotsCategory业务结束，结果:{}",r);
        return r;
    }

    /**
     * 分页查询
     *
     * @param pageParam
     * @return
     */
    @Override
    public R page(PageParam pageParam) {

        //分页参数
        IPage<Category> page = new Page<>(pageParam.getCurrentPage()
                ,pageParam.getPageSize());
        //查询参数获取
        page = categoryMapper.selectPage(page, null);

        List<Category> records = page.getRecords();
        long total = page.getTotal();

        R r = R.ok("查询类别数据成功!", records, total);

        log.info("CategoryServiceImpl.page业务结束，结果:{}",r);

        return r;
    }

    /**
     * 保存类别数据
     *
     * @param category
     * @return
     */
    @Override
    public R save(Category category) {

        int rows = categoryMapper.insert(category);

        if (rows > 0){
            return R.ok("类别保存成功!");
        }

        return R.fail("类别保存失败!");
    }

    /**
     * 删除对应的类别! 需要判断是否被引用
     *
     * @param categoryId
     * @return
     */
    @Override
    public R remove(Integer categoryId) {

        //调用商品服务,查询类别对应的商品数量
        long count = productClient.count(categoryId);
        //判断数量,如果有引用,不能删除,反之可以删除
        if (count > 0){

            return R.fail("无法删除类别,有:"+count+"件商品引用!");
        }

        int rows = categoryMapper.deleteById(categoryId);

        if (rows == 0){

            return R.fail("删除类别失败!");
        }
        return R.ok("类别删除成功!");
    }

    /**
     * 修改类别名
     *
     * @param category
     * @return
     */
    @Override
    public R update(Category category) {

        int rows = categoryMapper.updateById(category);

        if (rows > 0){
            return R.ok("类别修改成功!");
        }

        return R.fail("类别修改失败!");
    }

}
