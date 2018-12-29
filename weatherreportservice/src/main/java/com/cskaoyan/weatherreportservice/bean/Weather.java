package com.cskaoyan.weatherreportservice.bean;

import java.util.List;

public class Weather {

    private Yesterday yesterday;
    private String city;
    private String aqi;
    private List<Forecast> forecast;
    private String ganmao;
    private String wendu;

    @Override
    public String toString() {
        return "Weather{" +
                "yesterday=" + yesterday +
                ", city='" + city + '\'' +
                ", aqi='" + aqi + '\'' +
                ", forecast=" + forecast +
                ", ganmao='" + ganmao + '\'' +
                ", wendu='" + wendu + '\'' +
                '}';
    }

    public Yesterday getYesterday() {
        return yesterday;
    }

    public void setYesterday(Yesterday yesterday) {
        this.yesterday = yesterday;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public List<Forecast> getForecast() {
        return forecast;
    }

    public void setForecast(List<Forecast> forecast) {
        this.forecast = forecast;
    }

    public String getGanmao() {
        return ganmao;
    }

    public void setGanmao(String ganmao) {
        this.ganmao = ganmao;
    }

    public String getWendu() {
        return wendu;
    }

    public void setWendu(String wendu) {
        this.wendu = wendu;
    }
}
