package br.com.softbank.usuario.integration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import br.com.softbank.usuario.dto.UsuarioResponseDTO;

@FeignClient("oauth2")
public interface Oauth2Integration {

	@GetMapping("/v1/oauth/usuario")
	public UsuarioResponseDTO getAuthenticatedUser(@RequestHeader String Authorization);
}
