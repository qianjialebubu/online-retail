package com.atguigu.collect.controller;

import com.atguigu.collect.service.CollectService;
import com.atguigu.param.CollectParam;
import com.atguigu.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**

 * @author qjl
 * @create 2023-02-04 0:10
 */
@RestController
@RequestMapping("/collect")
public class CollectController {
    @Autowired
    private CollectService collectService;
    @PostMapping("save")
    public R save(@RequestBody CollectParam collectParam){
        return collectService.save(collectParam);
    }
    @PostMapping("list")
    public Object list(@RequestBody CollectParam collectParam){

        return collectService.list(collectParam);
    }

    @PostMapping("remove")
    public Object remove(@RequestBody CollectParam collectParam){

        return collectService.remove(collectParam);
    }
}
