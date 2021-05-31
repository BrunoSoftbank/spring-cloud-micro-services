package br.com.softbank.relatorio.integration;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import br.com.softbank.relatorio.dto.LaboratorioDTO;

@FeignClient("laboratorios")
public interface LaboratorioIntegration {

	@GetMapping("/v1/laboratorios")
	public List<LaboratorioDTO> findAll(@RequestHeader String Authorization);
}
