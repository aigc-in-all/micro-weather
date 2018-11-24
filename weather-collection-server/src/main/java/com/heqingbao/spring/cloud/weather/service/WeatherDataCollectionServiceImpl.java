package com.heqingbao.spring.cloud.weather.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@Service
public class WeatherDataCollectionServiceImpl implements WeatherDataCollectionService {

    private static final Logger logger = LoggerFactory.getLogger(WeatherDataCollectionServiceImpl.class);

    private static final String WEATHER_URI = "http://wthrcdn.etouch.cn/weather_mini";

    private static final long TIME_OUT = 1800L;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void syncDataByCityId(String cityId) {
        String url = WEATHER_URI + "?citykey=" + cityId;
        saveWeatherData(url);
    }

    /**
     * 把天气数据放在缓存中
     *
     * @param uri
     */
    private void saveWeatherData(String uri) {
        String key = uri;
        String strBody = null;
        ValueOperations<String, String> ops = redisTemplate.opsForValue();

        // 调用服务接口来获取天气数据
        ResponseEntity<String> respString = restTemplate.getForEntity(uri, String.class);

        if (respString.getStatusCodeValue() == 200) {
            strBody = respString.getBody();
        }

        // 数据写入缓存
        ops.set(key, strBody, TIME_OUT, TimeUnit.SECONDS);
    }
}
