package com.heqingbao.spring.cloud.weather.service;

import com.heqingbao.spring.cloud.weather.vo.WeatherResponse;

public interface WeatherDataService {

    /**
     * 根据城市ID查询天气信息
     *
     * @param cityId
     * @return
     */
    WeatherResponse getDataByCityId(String cityId);

    /**
     * 根据城市名称查询天气数据
     *
     * @param cityName
     * @return
     */
    WeatherResponse getDataByCityName(String cityName);

}
