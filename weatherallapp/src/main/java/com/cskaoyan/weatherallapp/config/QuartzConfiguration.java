package com.cskaoyan.weatherallapp.config;

import com.cskaoyan.weatherallapp.job.WeatherDataSyncJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 定时任务
 */
@Configuration
public class QuartzConfiguration {

    //我们来实现一个任务类
    private  static final int TIME_INTERVAL=60*5;

    //实现两个bean
    //job是什么？
    @Bean
    public JobDetail getWeatherDataFromServer(){
        //builder模式！！！
        /*JobBuilder jobBuilder = JobBuilder.newJob(WeatherDataSyncJob.class);
        jobBuilder.withIdentity("WeatherDataSyncJob");
        jobBuilder.storeDurably();
        JobDetail build = jobBuilder.build();*/
        return JobBuilder.newJob(WeatherDataSyncJob.class).withIdentity("weatherDataSyncJob").storeDurably().build();
    }

    //job是什么时候启动
    @Bean
    public Trigger weatherDataSyncJobTrigger(){
        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(TIME_INTERVAL).repeatForever();
        return TriggerBuilder.newTrigger().forJob(getWeatherDataFromServer()).withIdentity("weatherDataSyncJobTrigger").withSchedule(simpleScheduleBuilder).build();
    }

}
