package com.cskaoyan.weatherdatacollectionservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 产生一个restTemplate实例
 */
@Configuration
public class RestTemplateConfig {

    @Autowired
    private RestTemplateBuilder builder;

    @Bean
    public RestTemplate restTemplate(){
        return builder.build();
    }
}
