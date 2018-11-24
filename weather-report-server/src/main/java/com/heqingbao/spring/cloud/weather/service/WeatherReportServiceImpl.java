package com.heqingbao.spring.cloud.weather.service;

import com.heqingbao.spring.cloud.weather.vo.Forecast;
import com.heqingbao.spring.cloud.weather.vo.Weather;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherReportServiceImpl implements WeatherReportService {

    @Override
    public Weather getDataByCityId(String cityId) {
        // TODO 改为由天气数据API微服务提供
//        WeatherResponse resp = weatherDataService.getDataByCityId(cityId);
//        return resp.getData();

        Weather data = new Weather();
        data.setAqi("81");
        data.setCity("深圳");
        data.setGanmao("容器感冒，多穿衣");
        data.setWendu("22");

        List<Forecast> forecastList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Forecast c1 = new Forecast();
            c1.setDate(String.valueOf(29 + i) + "日");
            c1.setType("晴");
            c1.setFengxiang("无风");
            c1.setHigh(String.valueOf(32 + i));
            c1.setLow("10");
            c1.setFengli("3级");
            forecastList.add(c1);
        }
        data.setForecast(forecastList);
        return data;
    }
}
