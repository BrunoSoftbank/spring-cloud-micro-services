package br.com.softbank.relatorio.integration;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import br.com.softbank.relatorio.dto.RelatorioUsuarioDTO;

@FeignClient("usuarios")
public interface UsuarioIntegration {

	@GetMapping("/v1/usuarios?limit=100")
	public List<RelatorioUsuarioDTO> findAll(@RequestHeader String Authorization);
}


