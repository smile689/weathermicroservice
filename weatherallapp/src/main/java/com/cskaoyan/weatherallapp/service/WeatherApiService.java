package com.cskaoyan.weatherallapp.service;

import com.cskaoyan.weatherallapp.bean.WeatherResponse;

import java.io.IOException;

public interface WeatherApiService {

    WeatherResponse getWeatherInfoByCityName(String cityName) throws Exception;

    WeatherResponse getWeatherInfoByCityId(String cityId) throws IOException;
}
