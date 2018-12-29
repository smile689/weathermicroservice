package com.cskaoyan.weathercitydataapiservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class WeathercitydataapiserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeathercitydataapiserviceApplication.class, args);
    }

}

