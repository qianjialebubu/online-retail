package com.atguigu.doc;

import com.atguigu.pojo.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qjl
 * @create 2023-02-03 15:44
 */
@Data
@NoArgsConstructor
public class ProductDoc extends Product {
    private String all;

    public ProductDoc(Product product) {
        super(product.getProductId(),product.getProductName(),
                product.getCategoryId(),product.getProductTitle(),
                product.getProductIntro(),product.getProductPicture(),
                product.getProductPrice(),product.getProductSellingPrice(),
                product.getProductNum(),product.getProductSales());
        this.all = product.getProductName()+product.getProductTitle()+product.getProductIntro();
    }
}
