package com.cskaoyan.weatherreportservice.controller;

import com.cskaoyan.weatherreportservice.bean.City;
import com.cskaoyan.weatherreportservice.bean.Weather;
import com.cskaoyan.weatherreportservice.client.CityClient;
import com.cskaoyan.weatherreportservice.service.WeatherReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/report")
public class WeatherReportController {

    @Autowired
    WeatherReportService weatherReportService;

    @Autowired
    CityClient cityClient;

    //根据前端需要的数据进行封装
    @RequestMapping("/cityId/{cityId}")
    public String getWeatherReportByCityId(@PathVariable("cityId") String cityId, Model model){
        Map<String, Object> reportModel=new HashMap<>();
        reportModel.put("title", "wangdao temperature");
        //List<City> allCities = cityService.getAllCities();
        //临时解决方案，一会调用别的微服务的接口
        /*List<City> allCities =new ArrayList<>();
        City city1 = new City();
        city1.setCityId("101281701");
        city1.setCityName("中山");
        city1.setCityCode("zhongshan");
        city1.setProvince("广东");
        allCities.add(city1);
        City city2 = new City();
        city2.setCityId("101282001");
        city2.setCityName("茂名");
        city2.setCityCode("maoming");
        city2.setProvince("广东");
        allCities.add(city2);*/
        List<City> allCities = cityClient.getAllCities();
        reportModel.put("cityList",allCities);
        reportModel.put("cityId", cityId);
        //还要通过cityid取到weather的bean
        Weather weatherByCityId = null;
        try {
            weatherByCityId = weatherReportService.getWeatherByCityId(cityId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        reportModel.put("report", weatherByCityId);
        model.addAttribute("reportModel",reportModel);
        return "/weather/report";
    }
}
