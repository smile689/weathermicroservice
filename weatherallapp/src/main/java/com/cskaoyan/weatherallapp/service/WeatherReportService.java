package com.cskaoyan.weatherallapp.service;

import com.cskaoyan.weatherallapp.bean.Weather;

import java.io.IOException;

public interface WeatherReportService {

    Weather getWeatherByCityId(String cityId) throws IOException;
}
