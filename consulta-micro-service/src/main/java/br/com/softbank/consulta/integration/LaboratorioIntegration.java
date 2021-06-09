package br.com.softbank.consulta.integration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import br.com.softbank.consulta.response.LaboratorioResponse;

@FeignClient("laboratorios")
public interface LaboratorioIntegration {

	@GetMapping("/v1/laboratorios/{id}")
	LaboratorioResponse findById(@RequestHeader String Authorization, @PathVariable Long id);
}
