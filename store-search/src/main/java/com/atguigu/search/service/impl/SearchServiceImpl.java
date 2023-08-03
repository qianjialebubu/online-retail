package com.atguigu.search.service.impl;

import com.atguigu.doc.ProductDoc;
import com.atguigu.param.ProductParamsSearch;
import com.atguigu.pojo.Product;
import com.atguigu.search.service.SearchService;
import com.atguigu.utils.R;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.search.TotalHits;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author qjl
 * @create 2023-02-03 16:14
 */
@Service
@Slf4j
public class SearchServiceImpl implements SearchService {
    @Autowired
    private RestHighLevelClient client;

    /**
     * 商品搜索
     * @param productParamsSearch
     * @return
     */
    @Override
    public R search(ProductParamsSearch productParamsSearch) throws JsonProcessingException {

        SearchRequest searchRequest = new SearchRequest("product");
        if (StringUtils.isEmpty(productParamsSearch.getSearch())){
            //如果为null,查询全部
            searchRequest.source().query(QueryBuilders.matchAllQuery());
        }else{
            //不为空 all字段进行搜索
            searchRequest.source().query(QueryBuilders.matchQuery("all",productParamsSearch.getSearch()));
        }

        //设置分页参数
        searchRequest.source().from(productParamsSearch.getFrom());
        searchRequest.source().size(productParamsSearch.getSize());

        SearchResponse response = null;
        try {
            response = client.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw  new RuntimeException(e);
        }

        //结果集解析
        //获取集中的结果
        SearchHits hits = response.getHits();

        //获取符合的数量
        long total = hits.getTotalHits().value;
        log.info("Total hits: " + total);

        SearchHit[] items = hits.getHits();

        List<Product> productList = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        for (SearchHit item : items) {
            //获取单挑json数据
            String json = item.getSourceAsString();
            Product product = objectMapper.readValue(json, Product.class);
            productList.add(product);
        }

        return R.ok(null,productList,total);
    }

    @Override
    public R save(Product product) throws IOException {
        IndexRequest indexRequest = new IndexRequest("product")
                .id(product.getProductId().toString());

        ProductDoc productDoc = new ProductDoc(product);

        ObjectMapper objectMapper = new ObjectMapper();
        String json  = objectMapper.writeValueAsString(productDoc);

        indexRequest.source(json, XContentType.JSON);

        client.index(indexRequest, RequestOptions.DEFAULT);
        return R.ok("数据同步成功");

    }

    @Override
    public R remove(Integer productId) throws IOException {

        DeleteRequest request = new DeleteRequest("product")
                .id(productId.toString());

        client.delete(request,RequestOptions.DEFAULT);
        return R.ok("删除成功");
    }
}