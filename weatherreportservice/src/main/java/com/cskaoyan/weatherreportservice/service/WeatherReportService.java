package com.cskaoyan.weatherreportservice.service;

import com.cskaoyan.weatherreportservice.bean.Weather;

import java.io.IOException;

public interface WeatherReportService {

    Weather getWeatherByCityId(String cityId) throws IOException;
}
