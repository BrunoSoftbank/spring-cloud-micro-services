package br.com.softbank.relatorio.integration;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import br.com.softbank.relatorio.dto.ConsultaDTO;

@FeignClient("consultas")
public interface ConsultaIntegration {

	@GetMapping("/v1/consultas")
	public List<ConsultaDTO> findAll(@RequestHeader String Authorization);
}
