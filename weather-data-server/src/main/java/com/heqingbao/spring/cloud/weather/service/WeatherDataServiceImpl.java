package com.heqingbao.spring.cloud.weather.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heqingbao.spring.cloud.weather.vo.WeatherResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class WeatherDataServiceImpl implements WeatherDataService {

    private static final Logger logger = LoggerFactory.getLogger(WeatherDataServiceImpl.class);

    private static final String WEATHER_URI = "http://wthrcdn.etouch.cn/weather_mini";

    private static final long TIME_OUT = 1800L;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public WeatherResponse getDataByCityId(String cityId) {
        String url = WEATHER_URI + "?citykey=" + cityId;
        return doGetWeatherData(url);
    }

    @Override
    public WeatherResponse getDataByCityName(String cityName) {
        String url = WEATHER_URI + "?city=" + cityName;
        return doGetWeatherData(url);
    }

    private WeatherResponse doGetWeatherData(String url) {
        ObjectMapper mapper = new ObjectMapper();
        WeatherResponse resp = null;

        String key = url;
        String strBody = null;
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        // 先查缓存
        if (redisTemplate.hasKey(key)) {
            logger.info("Redis has data");
            strBody = ops.get(key);
        } else {
            logger.info("Redis don't has data");
            // 没有缓存，抛出异常
            throw new RuntimeException("Don't has data");
        }

        try {
            resp = mapper.readValue(strBody, WeatherResponse.class);
        } catch (Exception e) {
            logger.error("Error!", e);
        }

        return resp;
    }
}
