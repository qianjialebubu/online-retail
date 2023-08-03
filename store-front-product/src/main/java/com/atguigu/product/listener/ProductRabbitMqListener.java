package com.atguigu.product.listener;

import com.atguigu.product.service.ProductService;
import com.atguigu.to.OrderToProduct;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author qjl
 * @create 2023-02-04 12:49
 */
@Component
public class ProductRabbitMqListener {
    @Autowired
    private ProductService productService;

    /**
     * 修改库存数据
     * @param productNumberParams
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "sub.queue"),
            exchange = @Exchange("topic.ex"),
            key = "sub.number"
    ))
    public void subNumber(List<OrderToProduct> orderToProducts){
        System.err.println("ProductListener.subNumber");
        System.err.println("productNumberParams = " + orderToProducts);

        //调用业务修改库存即可
        productService.subNumber(orderToProducts);
    }
}
