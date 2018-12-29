package com.cskaoyan.weatherallapp.service.impl;

import com.cskaoyan.weatherallapp.bean.Weather;
import com.cskaoyan.weatherallapp.bean.WeatherResponse;
import com.cskaoyan.weatherallapp.service.WeatherApiService;
import com.cskaoyan.weatherallapp.service.WeatherReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class WeatherReportServiceImpl implements WeatherReportService {

    @Autowired
    WeatherApiService weatherApiService;

    @Override
    public Weather getWeatherByCityId(String cityId) throws IOException {
        WeatherResponse weatherInfoByCityId = weatherApiService.getWeatherInfoByCityId(cityId);
        Weather data = weatherInfoByCityId.getData();
        return data;
    }
}
