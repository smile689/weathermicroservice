package com.cskaoyan.weatherdataapiservice.service;

import com.cskaoyan.weatherdataapiservice.bean.WeatherResponse;

import java.io.IOException;

public interface WeatherApiService {

    WeatherResponse getWeatherInfoByCityName(String cityName) throws Exception;

    WeatherResponse getWeatherInfoByCityId(String cityId) throws Exception;
}
