package br.com.softbank.batch.config;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.softbank.batch.job.LaboratorioScheduleJob;

@Configuration
public class QuartzConfig {

	@Value("${intervalInHours}")
	private Integer intervalInHours;

	@Bean
	public JobDetail quartzJobDetail() {
		return JobBuilder.newJob(LaboratorioScheduleJob.class)
				.storeDurably().build();
	}
	
	@Bean
	public Trigger jobTrigger() {
		SimpleScheduleBuilder schedBuilder = SimpleScheduleBuilder
				.simpleSchedule()
				.withIntervalInHours(intervalInHours)
				.repeatForever();
		return TriggerBuilder.newTrigger().forJob(quartzJobDetail())
				.withSchedule(schedBuilder)	
				.build();
	}
}
