package com.cskaoyan.microserviceeurekaclientfeign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(serviceId ="myfirstclient" )
public interface FirstServiceApi {

    @RequestMapping("/hello")
    String hello();
}
