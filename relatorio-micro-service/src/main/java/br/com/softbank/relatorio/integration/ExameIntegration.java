package br.com.softbank.relatorio.integration;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import br.com.softbank.relatorio.response.ExameResponse;

@FeignClient("exames")
public interface ExameIntegration {

	@GetMapping("/v1/exames")
	public List<ExameResponse> findAll(@RequestHeader String Authorization);
}
