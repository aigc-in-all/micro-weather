package com.heqingbao.spring.cloud.weather.controller;

import com.heqingbao.spring.cloud.weather.service.CityClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityController {

    @Autowired
    private CityClient cityClient;

    @GetMapping("/cities")
    public String listCity() {
        String body = cityClient.listCity();
        return body;
    }
}
