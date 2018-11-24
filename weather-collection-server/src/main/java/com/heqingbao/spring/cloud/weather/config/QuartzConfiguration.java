package com.heqingbao.spring.cloud.weather.config;

import com.heqingbao.spring.cloud.weather.job.WeatherDataSyncJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfiguration {

    // JobDetail
    @Bean
    public JobDetail weatherDataSyncJobDetail() {
        return JobBuilder.newJob(WeatherDataSyncJob.class)
                .withIdentity("weatherDataSyncJob")
                .storeDurably()
                .build();
    }

    // Trigger
    @Bean
    public Trigger weatherDataSyncTrigger() {
        ScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(1800)
                .repeatForever();
        return TriggerBuilder.newTrigger()
                .forJob(weatherDataSyncJobDetail())
                .withIdentity("weatherDataSyncTrigger")
                .withSchedule(scheduleBuilder)
                .build();
    }
}
