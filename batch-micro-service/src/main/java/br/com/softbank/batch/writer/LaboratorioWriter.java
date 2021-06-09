package br.com.softbank.batch.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import br.com.softbank.batch.integration.RestIntegration;
import br.com.softbank.batch.request.LaboratorioRequest;

@Component
public class LaboratorioWriter implements ItemWriter<LaboratorioRequest> {
	
	@Autowired
	private RestIntegration restIntegration;

	/*
	@Bean
	public ItemWriter<LaboratorioRequestDTO> laboratorioWriter() {
		return items -> {
			items.forEach(item -> {
				restIntegration.salvar(item);
			});
		};
	}
	*/

	@Override
	public void write(List<? extends LaboratorioRequest> items) throws Exception {
		items.forEach(item -> {
			restIntegration.salvar(item);
		});	
	}
}
