package com.jzo2o.foundations.controller.inner;

import com.jzo2o.api.foundations.dto.response.ServeAggregationResDTO;
import com.jzo2o.foundations.service.IServeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

//内部接口 - 服务相关接口
@RestController
@RequestMapping(value = "/inner/serve")
@ApiOperation("内部接口 - 服务相关接口")
public class InnerServeController {

    @Resource
    private IServeService serveService;

    //根据ID查询服务详情
    @GetMapping("/{id}")
    public ServeAggregationResDTO findById(@PathVariable("id")Long id){
        return serveService.findServeDetailById(id);
    }
}