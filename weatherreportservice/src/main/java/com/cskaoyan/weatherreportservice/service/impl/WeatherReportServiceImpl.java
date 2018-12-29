package com.cskaoyan.weatherreportservice.service.impl;

import com.cskaoyan.weatherreportservice.bean.Weather;
import com.cskaoyan.weatherreportservice.bean.WeatherResponse;
import com.cskaoyan.weatherreportservice.client.WeatherDataClient;
import com.cskaoyan.weatherreportservice.service.WeatherReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class WeatherReportServiceImpl implements WeatherReportService {

    @Autowired
    WeatherDataClient weatherDataClient;

    @Override
    public Weather getWeatherByCityId(String cityId) throws IOException {
        //WeatherResponse weatherInfoByCityId = weatherApiService.getWeatherInfoByCityId(cityId);

        //此处需要调用其他的微服务的api
        /*WeatherResponse weatherResponse =new WeatherResponse();
        weatherResponse.setDesc("OK");
        weatherResponse.setStatus(1000);
        Weather weather=new Weather();
        weather.setCity("惠州");
        weather.setAqi("123");
        weather.setWendu("14");
        weather.setGanmao("相对今天出现了较大幅度降温，较易发生感冒，体质较弱的朋友请注意适当防护");
        weatherResponse.setData(weather);*/

        WeatherResponse weatherInfoByCityId = weatherDataClient.getWeatherInfoByCityId(cityId);

        Weather data = weatherInfoByCityId.getData();
        return data;
    }
}
