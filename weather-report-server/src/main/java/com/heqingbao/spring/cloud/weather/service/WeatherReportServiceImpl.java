package com.heqingbao.spring.cloud.weather.service;

import com.heqingbao.spring.cloud.weather.vo.Weather;
import com.heqingbao.spring.cloud.weather.vo.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherReportServiceImpl implements WeatherReportService {

    @Autowired
    private DataClient dataClient;

    @Override
    public Weather getDataByCityId(String cityId) {
        // 由天气数据API微服务提供
        WeatherResponse resp = dataClient.getDataByCityId(cityId);
        return resp.getData();
    }
}
