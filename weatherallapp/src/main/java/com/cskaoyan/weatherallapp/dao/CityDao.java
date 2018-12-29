package com.cskaoyan.weatherallapp.dao;

import com.cskaoyan.weatherallapp.bean.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CityDao {

    @Select("select * from city;")
    List<City> queryAllCities();

}
