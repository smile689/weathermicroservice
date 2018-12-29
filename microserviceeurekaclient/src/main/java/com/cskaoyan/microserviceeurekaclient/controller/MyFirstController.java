package com.cskaoyan.microserviceeurekaclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//如果不哟个json形式，那么string返回值就会找模板试图，认为是个地址
@RestController
public class MyFirstController {

    @Value("${cskaoyan.username}")
    String username;

    @RequestMapping("/hello")
    public String hello(){
        return "hello, eureka!4"+username;
    }
}
