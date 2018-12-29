package com.cskaoyan.weatherdatacollectionservice.job;

import com.cskaoyan.weatherdatacollectionservice.bean.City;
import com.cskaoyan.weatherdatacollectionservice.client.CityClient;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * 定时任务
 */
@Component
public class WeatherDataSyncJob extends QuartzJobBean {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CityClient cityClient;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final String SERVER_URI="http://wthrcdn.etouch.cn/weather_mini";
    //private final static Logger logger= LoggerFactory.getLogger(WeatherDataSyncJob.class);
    //具体定时执行的操作
    //每执行一次去server去数据，放到redis缓存
    //需要去调用一个api取出所有城市！然后每个城市遍历获得对应的天气数据
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("get Data from server, start!");
        //logger.info("logger: get Data from server, start!");
        ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
        String jsonString="";
        //数据采集服务需要调用城市数据api微服务的一个接口，去获取城市列表
        //List<City> allCities = cityService.getAllCities();
        /*List<City> allCities =new ArrayList<>();

        City city1 = new City();
        city1.setCityId("101281701");
        city1.setCityName("中山");
        city1.setCityCode("zhongshan");
        city1.setProvince("广东");
        allCities.add(city1);

        City city2 = new City();
        city2.setCityId("101282001");
        city2.setCityName("茂名");
        city2.setCityCode("maoming");
        city2.setProvince("广东");
        allCities.add(city2);*/
        List<City> allCities = cityClient.getAllCities();
        for (City city: allCities) {
            String keyName=SERVER_URI+"?city="+city.getCityName();
            String keyId=SERVER_URI+"?citykey="+city.getCityId();
            //从服务器取数据！
            ResponseEntity<String> respString = restTemplate.getForEntity(keyName, String.class);
            if(respString.getStatusCodeValue()==200){
                jsonString = respString.getBody();
                System.out.println(jsonString);
                //存到redis缓存
                //是否要设置过期时间？其实也没必要，后面自动更新
                stringStringValueOperations.set(keyName, jsonString);
                //可以牺牲空间同时放id和name两套，也可以牺牲时间，用id去查name，
                // redis里只有一套
                stringStringValueOperations.set(keyId, jsonString);
            }
        }


        System.out.println("get Data from server, end!");
    }
}
