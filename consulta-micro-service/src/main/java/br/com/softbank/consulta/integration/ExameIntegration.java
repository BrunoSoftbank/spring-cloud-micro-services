package br.com.softbank.consulta.integration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import br.com.softbank.consulta.dto.ExameDTO;

@FeignClient("exames")
public interface ExameIntegration {

	@GetMapping("/v1/exames/{id}")
	ExameDTO findById(@RequestHeader String Authorization, @PathVariable Long id);
}
