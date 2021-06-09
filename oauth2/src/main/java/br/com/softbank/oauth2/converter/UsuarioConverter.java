package br.com.softbank.oauth2.converter;

import org.springframework.stereotype.Component;

import br.com.softbank.oauth2.domain.Usuario;
import br.com.softbank.oauth2.response.UsuarioResponse;

@Component
public class UsuarioConverter {

	public UsuarioResponse convertUsuarioToUsuarioResponse(Usuario entity) {
		return new UsuarioResponse(entity.getId(), entity.getNome(), entity.getUsername(), entity.getAuthorities().stream().findFirst().get().getAuthority());

	}
}
