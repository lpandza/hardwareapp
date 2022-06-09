package hr.tvz.pandza.hardwareapp.scheduler;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

@Configuration
public class SchedulerConfig {

    @Bean
    public JobDetail getAvailableHardwareJobDetail(){
        return JobBuilder.newJob().ofType(AvailableHardware.class)
                .storeDurably()
                .withIdentity("availableHardwareJob")
                .build();
    }

    @Bean
    public Trigger availableHardwareJobTrigger() {
        return TriggerBuilder.newTrigger().forJob(getAvailableHardwareJobDetail())
                .withIdentity("availableHardwareTrigger")
                .withSchedule(simpleSchedule().withIntervalInSeconds(10).repeatForever())
                .build();
    }

}
