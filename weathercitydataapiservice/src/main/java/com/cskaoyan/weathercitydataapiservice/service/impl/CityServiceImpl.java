package com.cskaoyan.weathercitydataapiservice.service.impl;


import com.cskaoyan.weathercitydataapiservice.bean.City;
import com.cskaoyan.weathercitydataapiservice.dao.CityDao;
import com.cskaoyan.weathercitydataapiservice.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    CityDao cityDao;

//利用api获得xml数据，解析
    //或者访问数据库获得
    @Override
    public List<City> getAllCities() {
        return cityDao.queryAllCities();
    }
}
