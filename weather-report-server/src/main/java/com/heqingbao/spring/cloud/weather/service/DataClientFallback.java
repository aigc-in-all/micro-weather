package com.heqingbao.spring.cloud.weather.service;

import com.heqingbao.spring.cloud.weather.vo.City;
import com.heqingbao.spring.cloud.weather.vo.WeatherResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataClientFallback implements DataClient {

    @Override
    public List<City> listCity() {
        List<City> cityList = new ArrayList<>();
        City city1 = new City();
        city1.setId("101280601");
        city1.setName("深圳");
        cityList.add(city1);

        City city2 = new City();
        city2.setId("101280301");
        city2.setName("惠州");
        cityList.add(city2);

        return cityList;
    }

    @Override
    public WeatherResponse getDataByCityId(String cityId) {
        return null;
    }
}
