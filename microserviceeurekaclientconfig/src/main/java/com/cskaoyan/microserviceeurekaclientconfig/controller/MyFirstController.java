package com.cskaoyan.microserviceeurekaclientconfig.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//如果不哟个json形式，那么string返回值就会找模板试图，认为是个地址
@RestController
public class MyFirstController {

    @RequestMapping("/hello")
    public String hello(){
        return "hello, eureka!4";
    }
}
