package br.com.softbank.batch.integration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import br.com.softbank.batch.dto.LaboratorioRequestDTO;

@FeignClient("laboratorios")
public interface LaboratorioIntegration {

	@GetMapping("/v1/laboratorios")
	public void save(@RequestHeader String Authorization, @RequestBody LaboratorioRequestDTO request);
}
