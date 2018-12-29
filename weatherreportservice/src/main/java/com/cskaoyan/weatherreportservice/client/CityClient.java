package com.cskaoyan.weatherreportservice.client;

import com.cskaoyan.weatherreportservice.bean.City;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 调用city微服务进程的方法
 */
@FeignClient(serviceId ="citydataapiservice" )
public interface CityClient {

    @RequestMapping("/listCities")
    List<City> getAllCities();
}
