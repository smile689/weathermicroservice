package com.cskaoyan.weatherallapp.service.impl;

import com.cskaoyan.weatherallapp.bean.WeatherResponse;
import com.cskaoyan.weatherallapp.service.WeatherApiService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

//要去调用其他服务器的气象接口
//通过代码访问url！使用原始的httpclient或者resttemplate
@Service
public class WeatherApiServiceImpl implements WeatherApiService {

    @Autowired
    RestTemplate restTemplate;

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
    private WeatherResponse getWeatherResponse(String requestUri) throws IOException {
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
            //服务器上请求数据
            ResponseEntity<String> respString = restTemplate.getForEntity(requestUri, String.class);
            if(respString.getStatusCodeValue()==200){
                jsonString = respString.getBody();
                System.out.println(jsonString);
                //存到redis缓存
                //是否要设置过期时间？其实也没必要，后面自动更新
                stringStringValueOperations.set(key, jsonString);
            }
        }
        //json数据转成object,转行为对应的对象
        weatherResponse=mapper.readValue(jsonString, WeatherResponse.class);
        return weatherResponse;
    }

    //http://wthrcdn.etouch.cn/weather_mini?citykey=101280301
    @Override
    public WeatherResponse getWeatherInfoByCityId(String cityId) throws IOException {
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
