package br.com.softbank.relatorio.integration;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import br.com.softbank.relatorio.dto.UsuarioDTO;

@FeignClient("usuarios")
public interface UsuarioIntegration {

	@GetMapping("/v1/usuarios?limit=100")
	public List<UsuarioDTO> findAll(@RequestHeader String Authorization);
}


