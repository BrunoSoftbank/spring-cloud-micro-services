package br.com.softbank.consulta.integration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import br.com.softbank.consulta.dto.UsuarioDTO;

@FeignClient("oauth2")
public interface Oauth2Integration {

	@GetMapping("/v1/oauth/usuario")
	public UsuarioDTO getAuthenticatedUser(@RequestHeader String Authorization);
}
