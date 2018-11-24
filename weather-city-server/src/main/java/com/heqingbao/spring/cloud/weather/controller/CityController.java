package com.heqingbao.spring.cloud.weather.controller;

import com.heqingbao.spring.cloud.weather.service.CityDataService;
import com.heqingbao.spring.cloud.weather.vo.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityDataService cityDataService;

    @RequestMapping()
    public List<City> listCity() throws Exception {
        return cityDataService.listCity();
    }
}
