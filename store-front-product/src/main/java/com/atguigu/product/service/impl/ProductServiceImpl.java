package com.atguigu.product.service.impl;

import com.atguigu.clients.CategoryClient;
import com.atguigu.clients.SearchClient;
import com.atguigu.param.*;
import com.atguigu.pojo.*;
import com.atguigu.product.mapper.PicturesMapper;
import com.atguigu.product.mapper.ProductMapper;
import com.atguigu.product.service.ProductService;
import com.atguigu.to.OrderToProduct;
import com.atguigu.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author qjl
 * @create 2023-02-01 19:53
 */
@Service
@Slf4j
public class ProductServiceImpl extends ServiceImpl<ProductMapper,Product> implements ProductService {
    @Autowired
    private CategoryClient categoryClient;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private PicturesMapper picturesMapper;
    @Autowired
    private SearchClient searchClient;
    @Autowired
    private RabbitTemplate rabbitTemplate;


    /**
     * 根据name查询热门商品数据，至多七条
     * @param categoryName
     * @return
     */
    @Override
    @Cacheable(value = "list.product",key = "#categoryName",cacheManager = "cacheManagerHour")
    public R promo(String categoryName) {
        R r = categoryClient.byName(categoryName);
        if (r.getCode().equals(R.FAIL_CODE)) {
            log.info("ProductServiceImpl.promo业务结束，结果:{}","类别查询失败!");
            return r;
        }
        // 类别服务中 data = category --- feign {json}  ----- product服务 LinkedHashMap jackson

        LinkedHashMap<String,Object>  map = (LinkedHashMap<String, Object>) r.getData();
        Integer categoryId = (Integer) map.get("category_id");
        System.out.println("categoryId"+categoryId);
        //封装查询参数
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_id",categoryId);
        queryWrapper.orderByDesc("product_sales");

        IPage<Product> page = new Page<>(1,7);

        //返回的是包装数据! 内部有对应的商品集合,也有分页的参数 例如: 总条数 总页数等等
        page = productMapper.selectPage(page, queryWrapper);

        List<Product> productList = page.getRecords(); //指定页的数据
        log.info("ProductList: " + productList.toString());
        long total = page.getTotal(); //获取总条数

        log.info("ProductServiceImpl.promo业务结束，结果:{}",productList);

        return R.ok("数据查询成功",productList);
    }

    /**
     * 多类别热门商品查询 根据类别名称集合! 至多查询7条!
     *   1. 调用类别服务
     *   2. 类别集合id查询商品
     *   3. 结果集封装即可
     * @param productHotParam 类别名称集合
     * @return r
     */
    @Override
    @Cacheable(value = "list.product",key = "#productHotParam.categoryName")
    public R hot(ProductHotParam productHotParam) {
        //1
        R r = categoryClient.hots(productHotParam);
        if(r.getCode().equals(R.FAIL_CODE)){
            log.info("ProductServiceImpl.hots业务结束，结果:{}",r.getMsg());
            return r;
        }
        List<Object> ids = (List<Object>) r.getData();

        //2
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        wrapper.in("category_id",ids);
        wrapper.orderByDesc("product_sales");
        IPage<Product> page = new Page<>(1, 7);
        page = productMapper.selectPage(page, wrapper);
        List<Product> productList = page.getRecords();
        R ok = R.ok("多类别热门商品查询成功!", productList);
        log.info("ProductServiceImpl.hots业务结束，结果:{}",ok);

        return ok;

    }

    @Override
    @Cacheable(value = "list.category",key = "#root.methodName")
    public R clist() {
        R r = categoryClient.list();
        log.info("ProductServiceImpl.clist业务结束，结果:{}",r);
        return r;
    }

    @Override
    @Cacheable(value = "list.product",key =
            "#productParamInteger.categoryID+" +
                    "'-'+#productParamInteger.currentPage+" +
                    "'-'+#productParamInteger.pageSize")
    public Object byCategory(ProductParamInteger productParamInteger) {
        List<Integer> categoryID = productParamInteger.getCategoryID();
        int currentPage = productParamInteger.getCurrentPage();
        int pageSize = productParamInteger.getPageSize();
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        if(categoryID!=null && categoryID.size()>0) {
            wrapper.in("category_id",categoryID);
        }
        IPage<Product> page = new Page<>(currentPage, pageSize);
        IPage<Product> iPage = productMapper.selectPage(page, wrapper);
        List<Product> productList = iPage.getRecords();
        long total = iPage.getTotal();
        R ok = R.ok(null, productList, total);
        log.info("ProductServiceImpl.byCategory业务结束，结果:{}",ok);
        return ok;
    }

    @Override
    @Cacheable(value = "list.product",key = "#productParamInteger.currentPage+"+"'-'+#productParamInteger.pageSize")
    public Object all(ProductParamInteger productParamInteger) {
        return byCategory(productParamInteger);
    }

    /**
     * 查询商品详情
     *
     * @param productID 商品id
     * @return
     */
    @Override
    @Cacheable(value = "product",key = "#productID")
    public Object detail(Integer productID) {

        Product product = productMapper.selectById(productID);

        R ok = R.ok(product);

        log.info("ProductServiceImpl.detail业务结束，结果:{}",ok);

        return ok;
    }

    @Override
    @Cacheable(value = "picture",key = "#productID")
    public R pictures(Integer productID) {
        QueryWrapper<Picture> wrapper = new QueryWrapper<>();
        wrapper.eq("product_id",productID);
        List<Picture> pictureList = picturesMapper.selectList(wrapper);
        R r = R.ok(pictureList);
        log.info("ProductServiceImpl.pictures业务结束，结果:{}",r);

        return r;
    }

    @Override
    public List<Product> list() {
        List<Product> products = productMapper.selectList(null);
        log.info("ProductServiceImpl.list执行完毕，长度为{}",products.size());
        return products;
    }

    @Override
    public R search(ProductParamsSearch productParamsSearch) {
        R r = searchClient.search(productParamsSearch);
        return r;
    }

    /**
     * 查询商品集合
     * @param productIdsParam
     * @return
     */
    @Cacheable(value = "list.product",key = "#productIdsParam.productIds")
    @Override
    public List<Product> ids(ProductIdsParam productIdsParam) {
        log.info("productIdsParam={}",productIdsParam);
        if(productIdsParam.getProductIds().size()==0){
            log.info("购物车数据是空");
            ArrayList<Product> list = new ArrayList<>();
            return list;
        }
        List<Integer> productIds = productIdsParam.getProductIds();

        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("product_id",productIds);
        List<Product> productList = productMapper.selectList(queryWrapper);
        log.info("productList={}",productList);
        return productList;
    }

    //productService
    /**
     * 修改商品库存
     *
     * @param orderToProducts
     */
    @Transactional
    @Override
    public void subNumber(List<OrderToProduct> orderToProducts) {
        //将productNumberParams转成map
        //使用id作为key, item做值, 比较相邻的两次key,如果相同,去掉重读!
        Map<Integer, OrderToProduct> orderToProductMap = orderToProducts.stream()
                .collect(Collectors.toMap(OrderToProduct::getProductId, v -> v));

        //封装商品集合
        Set<Integer> productIds = orderToProductMap.keySet();

        //查询
        List<Product> productList = productMapper.selectBatchIds(productIds);
        //修改

        for (Product product : productList) {
            //设置新库存
            product.setProductNum(product.getProductNum() -
                    orderToProductMap.get(product.getProductId()).getNum());
            //设置销售量
            product.setProductSales(product.getProductSales() +
                    orderToProductMap.get(product.getProductId()).getNum());
        }

        //批量数据更新
        this.updateBatchById(productList);
        log.info("库存与销售量更新成功");
    }

    /**
     * 类别对应商品数量
     *
     * @param categoryId
     * @return
     */
    @Override
    public Long categoryCount(Integer categoryId) {

        QueryWrapper<Product> queryWrapper  =
                new QueryWrapper<>();

        queryWrapper.eq("category_id",categoryId);

        Long count = productMapper.selectCount(queryWrapper);

        log.info("ProductServiceImpl.categoryCount业务结束，结果:{}",count);
        return count;
    }

    /**
     * 保存商品信息
     *   1.保存商品信息
     *   2.保存商品图片信息
     *   3.发送消息,es库进行插入
     * @param productSaveParam
     * @return
     */
    @Transactional
    @Override
    @CacheEvict(value ="list.product",allEntries = true)
    public R save(ProductSaveParam productSaveParam) {

        Product product = new Product();
        //参数赋值
        BeanUtils.copyProperties(productSaveParam,product);
        //商品数据保存
        //显示
        Integer productId = product.getProductId();
        String productPicture = product.getProductPicture();
        log.info("ProductId: " + productId);
        log.info("ProductPicture:" + productPicture.length());

        int rows = productMapper.insert(product);

        if (rows == 0){
            return R.fail("商品保存失败!");
        }

        //进行Picture对象封装
        String pictures = productSaveParam.getPictures();

        if (!StringUtils.isEmpty(pictures)){
            //$ + - * | / ？^符号在正则表达示中有相应的不同意义。
            //一般来讲只需要加[]、或是\\即可
            String[] urls = pictures.split("\\+");
            ArrayList<Picture> pictureArrayList = new ArrayList<>();
            for (String url : urls) {
                Picture picture = new Picture();
                picture.setProductId(product.getProductId());
                picture.setProductPicture(url);
                picturesMapper.insert(picture);
            }
        }


        //保存成功,进行发送消息,product插入到es库中
        rabbitTemplate.convertAndSend("topic.ex","insert.product",product);
//        searchClient.saveOrUpdate(product);
        return R.ok("商品数据保存成功!");

    }
    /**
     * 商品数据进行更新
     *   1.更新数据
     *   2.通知es服务,进行更新数据
     * @param product
     * @return
     */
    @Override
    public R update(Product product) {

        int rows = baseMapper.updateById(product);

        if (rows == 0){
            return R.fail("商品数据更新失败!");
        }
        //es更新就是插入覆盖即可~
        rabbitTemplate.convertAndSend("topic.ex",
                "insert.product",product);

        return R.ok("商品数据更新成功!");
    }



}
