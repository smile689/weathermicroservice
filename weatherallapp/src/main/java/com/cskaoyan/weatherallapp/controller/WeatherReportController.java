package com.cskaoyan.weatherallapp.controller;

import com.cskaoyan.weatherallapp.bean.City;
import com.cskaoyan.weatherallapp.bean.Weather;
import com.cskaoyan.weatherallapp.service.CityService;
import com.cskaoyan.weatherallapp.service.WeatherReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/report")
public class WeatherReportController {

    @Autowired
    CityService cityService;

    @Autowired
    WeatherReportService weatherReportService;

    //根据前端需要的数据进行封装
    @RequestMapping("/cityId/{cityId}")
    public String getWeatherReportByCityId(@PathVariable("cityId") String cityId, Model model){
        Map<String, Object> reportModel=new HashMap<>();
        reportModel.put("title", "wangdao temperature");
        List<City> allCities = cityService.getAllCities();
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
