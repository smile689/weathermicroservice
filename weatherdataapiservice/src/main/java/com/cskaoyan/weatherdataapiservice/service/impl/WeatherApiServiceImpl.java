package com.cskaoyan.weatherdataapiservice.service.impl;

import com.cskaoyan.weatherdataapiservice.bean.WeatherResponse;
import com.cskaoyan.weatherdataapiservice.service.WeatherApiService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class WeatherApiServiceImpl implements WeatherApiService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //通过代码去请求一个服务器
    private static final String SERVER_URI="http://wthrcdn.etouch.cn/weather_mini";

    //http://wthrcdn.etouch.cn/weather_mini?city=北京
    @Override
    public WeatherResponse getWeatherInfoByCityName(String cityName) throws Exception {
        String requestUri=SERVER_URI+"?city="+cityName;
        return getWeatherResponse(requestUri);
    }

    /**
     * 如果redis里有这个key，则直接去取，
     * 如果redis里没有这个key？则去请求server
     * @param requestUri
     * @return
     * @throws IOException
     */
    private WeatherResponse getWeatherResponse(String requestUri) throws Exception {
        WeatherResponse weatherResponse=null;
        ObjectMapper mapper=new ObjectMapper();
        String key=requestUri;
        ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
        String jsonString="";
        //判断当前redis缓存中是否有这条记录
        if(stringRedisTemplate.hasKey(key)){
            //redis有
            System.out.println("from redis");
            jsonString= stringStringValueOperations.get(key);

        }else{
            System.out.println("from server");
            //redis没有就报错
            throw new Exception("redis has no data"+requestUri);
        }
        //json数据转成object,转行为对应的对象
        weatherResponse=mapper.readValue(jsonString, WeatherResponse.class);
        return weatherResponse;
    }

    //http://wthrcdn.etouch.cn/weather_mini?citykey=101280301
    @Override
    public WeatherResponse getWeatherInfoByCityId(String cityId) throws Exception {
        String requestUri=SERVER_URI+"?citykey="+cityId;
        return getWeatherResponse(requestUri);
    }

    //httpclient代码
    /*HttpClient httpClient = new DefaultHttpClient();

        // get method
        HttpGet httpGet = new HttpGet("http://wthrcdn.etouch.cn/weather_mini?city="+cityName);

        httpGet.setHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8"));
        httpGet.setHeader(new BasicHeader("Accept", "text/json;charset=utf-8"));

        //response
        HttpResponse response = null;
        try{
            response = httpClient.execute(httpGet);
        }catch (Exception e) {}

        //get response into String
        String temp="";
        try{
            HttpEntity entity = response.getEntity();


            temp= EntityUtils.toString(entity,"utf-8");

            System.out.println(temp);

        }catch (Exception e) {


        }*/
}
