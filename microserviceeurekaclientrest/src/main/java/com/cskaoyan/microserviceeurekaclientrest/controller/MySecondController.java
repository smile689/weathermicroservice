package com.cskaoyan.microserviceeurekaclientrest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MySecondController {

    //RestTemplate服务之间调用的方式1

    //写死了url不好
    @RequestMapping("/callanother")
    public String callAnotherAPI(){
        //通过调用另一个服务的api获取哪个服务返回的字符串
        RestTemplate restTemplate = new RestTemplate();
        //这里的参数url就是我们需要访问的另一个微服务的服务的url
        String forObject = restTemplate.getForObject("http://localhost:10086/hello", String.class);
        return forObject;
    }

//不写死,RestTemplate服务之间调用的方式2
    //可以不靠ip，然后可以加负载均衡
   /* @Autowired
    LoadBalancerClient loadBalancerClient;

    @RequestMapping("/callanother2")
    public String callAnotherAPI2(){
        //通过调用另一个服务的api获取哪个服务返回的字符串
        RestTemplate restTemplate = new RestTemplate();
        //根据serviceid去获取，就是eureka中显示的名字，然后对应module的配置文件
        ServiceInstance serviceInstance = loadBalancerClient.choose("MYFIRSTCLIENT");
        String host = serviceInstance.getHost();
        int port = serviceInstance.getPort();

        //这里的参数url就是我们需要访问的另一个微服务的服务的url
        String forObject = restTemplate.getForObject("http://"+host+":"+port+"/hello", String.class);
        return forObject;
    }*/

   //方法三，用一个resttemplate的配置类，直接调用服务名
    @Autowired
    RestTemplate restTemplate;
    @RequestMapping("/callanother3")
    public String callAnotherAPI3(){

        //这里的参数url就是我们需要访问的另一个微服务的服务的url
        String forObject = restTemplate.getForObject("http://MYFIRSTCLIENT/hello", String.class);
        return forObject;
    }
}
