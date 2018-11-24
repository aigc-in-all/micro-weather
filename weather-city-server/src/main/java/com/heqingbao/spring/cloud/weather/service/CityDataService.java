package com.heqingbao.spring.cloud.weather.service;

import com.heqingbao.spring.cloud.weather.vo.City;

import java.util.List;

public interface CityDataService {

    /**
     * 获取City列表
     *
     * @return
     * @throws Exception
     */
    List<City> listCity() throws Exception;
}
