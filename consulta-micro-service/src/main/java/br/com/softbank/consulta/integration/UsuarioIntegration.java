package br.com.softbank.consulta.integration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import br.com.softbank.consulta.dto.UsuarioDTO;

@FeignClient("usuarios")
public interface UsuarioIntegration {

	@GetMapping("/v1/usuarios/{id}")
	public UsuarioDTO findById(@RequestHeader String Authorization, @PathVariable Long id);
}
