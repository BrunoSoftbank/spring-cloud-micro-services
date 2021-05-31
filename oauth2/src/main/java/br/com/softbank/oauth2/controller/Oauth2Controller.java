package br.com.softbank.oauth2.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.softbank.oauth2.domain.Usuario;
import br.com.softbank.oauth2.domain.UsuarioDTO;

@RestController
@RequestMapping("/v1/oauth")
public class Oauth2Controller {

	@GetMapping("/usuario")
	public UsuarioDTO user(@RequestHeader String Authorization) {	
		Usuario principal = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return new UsuarioDTO(principal.getId(), principal.getNome(), principal.getUsername(), principal.getAuthorities().stream().findFirst().get().getAuthority());
	}
}
