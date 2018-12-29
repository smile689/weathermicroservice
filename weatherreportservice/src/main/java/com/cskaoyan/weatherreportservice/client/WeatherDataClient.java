package com.cskaoyan.weatherreportservice.client;

import com.cskaoyan.weatherreportservice.bean.WeatherResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 调用weatherapi获得数据
 */
@FeignClient(serviceId ="weatherdataapiservice" )
public interface WeatherDataClient {

    @RequestMapping("/weather/cityName/{cityName}")
    WeatherResponse getWeatherInfoByCityName(@PathVariable("cityName") String cityName);

    @RequestMapping("/weather/cityId/{cityId}")
    WeatherResponse getWeatherInfoByCityId(@PathVariable("cityId") String cityId);
}
