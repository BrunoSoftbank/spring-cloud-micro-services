package br.com.softbank.batch.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.softbank.batch.enuns.ResourceEnum;
import br.com.softbank.batch.enuns.SftpActionEnum;
import br.com.softbank.batch.integration.SftpIntegration;

@Configuration
public class DownloadLaboratorioFileStepConfig {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private SftpIntegration sftpIntegration;

	@Bean
	public Step downloadLaboratorioFileStep() {
		return stepBuilderFactory.get("downloadLaboratorioFileStep").tasklet(new Tasklet() {		
			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				sftpIntegration.downloadOrDelete(ResourceEnum.LABORATORIOS, SftpActionEnum.DOWNLOAD);
				return RepeatStatus.FINISHED;
			}
		}).build();
	}
}
