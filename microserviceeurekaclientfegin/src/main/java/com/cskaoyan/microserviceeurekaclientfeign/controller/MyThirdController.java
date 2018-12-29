package com.cskaoyan.microserviceeurekaclientfeign.controller;

import com.cskaoyan.microserviceeurekaclientfeign.client.FirstServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MyThirdController {

    //使用Feign的形式来进行微服务之间的调用 伪RPC调用

    @Autowired
    FirstServiceApi firstServiceApi;

    @RequestMapping("/callanother4")
    public String callAnotherAPI4(){

        String hello = firstServiceApi.hello();

        return hello;
    }

}
