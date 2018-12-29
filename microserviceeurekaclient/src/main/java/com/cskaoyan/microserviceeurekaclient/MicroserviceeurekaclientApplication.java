package com.cskaoyan.microserviceeurekaclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroserviceeurekaclientApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceeurekaclientApplication.class, args);
    }

}

