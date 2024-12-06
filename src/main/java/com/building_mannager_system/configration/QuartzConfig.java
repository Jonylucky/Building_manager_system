package com.building_mannager_system.configration;


import com.building_mannager_system.component.CustomerCheckJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail customerCheckJobDetail() {
        return JobBuilder.newJob(CustomerCheckJob.class)
                .withIdentity("CustomerCheckJob").storeDurably()
                .build();

    }
    @Bean
    public Trigger customerCheckTrigger() {
        return TriggerBuilder
                .newTrigger()
                .forJob(customerCheckJobDetail())
                .withIdentity("customerCheckJobTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 0 * * ?"))//thực thi vào lúc 00.00h(start 00.00h )
                .build();
    }
}
