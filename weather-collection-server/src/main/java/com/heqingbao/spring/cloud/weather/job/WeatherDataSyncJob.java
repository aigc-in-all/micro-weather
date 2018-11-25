package com.heqingbao.spring.cloud.weather.job;

import com.heqingbao.spring.cloud.weather.service.CityClient;
import com.heqingbao.spring.cloud.weather.service.WeatherDataCollectionService;
import com.heqingbao.spring.cloud.weather.vo.City;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

public class WeatherDataSyncJob extends QuartzJobBean {

    private static final Logger logger = LoggerFactory.getLogger(WeatherDataSyncJob.class);

    @Autowired
    private WeatherDataCollectionService dataCollectionService;

    @Autowired
    private CityClient cityClient;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        logger.info("Weather Data Sync Job. Start!");

        List<City> cityList = null;

        try {
            // 由城市数据API微服务提供数据
            cityList = cityClient.listCity();
        } catch (Exception e) {
            logger.error("Exception!", e);
        }

        for (City city : cityList) {
            String cityId = city.getId();
            logger.info("Weather Data Sync Job, cityId: " + cityId);

            dataCollectionService.syncDataByCityId(cityId);
        }

        logger.info("Weather Data Sync Job. End!");
    }
}
