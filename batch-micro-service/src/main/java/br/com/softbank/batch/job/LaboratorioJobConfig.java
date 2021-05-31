package br.com.softbank.batch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class LaboratorioJobConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Bean
	public Job laboratorioJob(Step downloadLaboratorioFileStep, Step salvarLaboratorioStep, Step deleteLaboratorioFileStep) {
		return jobBuilderFactory.get("laboratorioJob").start(downloadLaboratorioFileStep).next(salvarLaboratorioStep)
				.next(deleteLaboratorioFileStep)
				.incrementer(new RunIdIncrementer()). build();
	}
}
