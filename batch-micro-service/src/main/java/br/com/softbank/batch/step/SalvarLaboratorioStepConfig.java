package br.com.softbank.batch.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.softbank.batch.dto.LaboratorioFromFileDTO;
import br.com.softbank.batch.request.LaboratorioRequest;

@Configuration
public class SalvarLaboratorioStepConfig {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Step salvarLaboratorioStep(
			ItemReader<LaboratorioFromFileDTO> laboratorioReader,
			ItemProcessor<LaboratorioFromFileDTO, LaboratorioRequest> laboratorioProcessor,
			ItemWriter<LaboratorioRequest> laboratorioWriter) {
		
		return stepBuilderFactory
				.get("salvarLaboratorioStep")			
				.<LaboratorioFromFileDTO, LaboratorioRequest>chunk(1000000)
				.reader(laboratorioReader)
				.processor(laboratorioProcessor)
				.writer(laboratorioWriter)
				.build();
	}
}
