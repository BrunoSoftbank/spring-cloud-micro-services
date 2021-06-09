package br.com.softbank.oauth2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.softbank.oauth2.converter.UsuarioConverter;
import br.com.softbank.oauth2.domain.Usuario;
import br.com.softbank.oauth2.response.UsuarioResponse;

@RestController
@RequestMapping("/v1/oauth")
public class Oauth2Controller {
	
	@Autowired
	private UsuarioConverter usuarioConverter;

	@GetMapping("/usuario")
	public UsuarioResponse user(@RequestHeader String Authorization) {
		return usuarioConverter.convertUsuarioToUsuarioResponse((Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal());		
	}
}
