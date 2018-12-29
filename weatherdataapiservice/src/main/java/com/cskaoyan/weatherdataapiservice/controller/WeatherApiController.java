package com.cskaoyan.weatherdataapiservice.controller;

import com.cskaoyan.weatherdataapiservice.bean.WeatherResponse;
import com.cskaoyan.weatherdataapiservice.service.WeatherApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * 写两个api通过cityname和id获取天气数据
 */
@RestController
@RequestMapping("/weather")
public class WeatherApiController {

    @Autowired
    WeatherApiService weatherApiService;

    @RequestMapping("/cityName/{cityName}")
    public WeatherResponse getWeatherInfoByCityName(@PathVariable("cityName") String cityName){
        WeatherResponse weatherInfoByCityName = null;
        try {
            weatherInfoByCityName = weatherApiService.getWeatherInfoByCityName(cityName);
        } catch (Exception e) {
            e.printStackTrace();
            //添加异常信息
            weatherInfoByCityName=new WeatherResponse();
            weatherInfoByCityName.setStatus(-1);
            weatherInfoByCityName.setDesc("no data!");
        }
        return weatherInfoByCityName;
    }

    @RequestMapping("/cityId/{cityId}")
    public WeatherResponse getWeatherInfoByCityId(@PathVariable("cityId") String cityId){
        WeatherResponse weatherInfoByCityId = null;
        try {
            weatherInfoByCityId = weatherApiService.getWeatherInfoByCityId(cityId);
        } catch (Exception e) {
            e.printStackTrace();
            weatherInfoByCityId=new WeatherResponse();
            weatherInfoByCityId.setStatus(-1);
            weatherInfoByCityId.setDesc("no data!");
        }
        return weatherInfoByCityId;
    }
}
