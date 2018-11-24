package com.heqingbao.spring.cloud.weather.controller;

import com.heqingbao.spring.cloud.weather.service.WeatherReportService;
import com.heqingbao.spring.cloud.weather.vo.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/report")
public class WeatherReportController {

    @Autowired
    private WeatherReportService weatherReportService;

    @GetMapping("/cityId/{cityId}")
    public ModelAndView getReportByCityId(@PathVariable("cityId") String cityId, Model model) throws Exception {
        model.addAttribute("title", "天气预报");
        model.addAttribute("cityId", cityId);
        // TODO: 2018/11/24 改为由城市数据API微服务来提供数据
//        model.addAttribute("cityList", cityDataService.listCity());
        List<City> cityList = new ArrayList<>();
        City city = new City();
        city.setId("101280101");
        city.setName("深圳");
        cityList.add(city);
        model.addAttribute("cityList", cityList);

        model.addAttribute("report", weatherReportService.getDataByCityId(cityId));
        return new ModelAndView("weather/report", "reportModel", model);
    }
}