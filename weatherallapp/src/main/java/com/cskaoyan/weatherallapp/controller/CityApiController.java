package com.cskaoyan.weatherallapp.controller;

import com.cskaoyan.weatherallapp.bean.City;
import com.cskaoyan.weatherallapp.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 返回所有城市
 */
@RestController
public class CityApiController {

    @Autowired
    CityService cityService;

    @GetMapping("/listCities")
    public List<City> getAllCities(){
        return cityService.getAllCities();
    }
}
