package br.com.softbank.oauth2.converter;

import org.springframework.stereotype.Component;

import br.com.softbank.oauth2.domain.Usuario;
import br.com.softbank.oauth2.dto.UsuarioDTO;

@Component
public class UsuarioConverter {

	public UsuarioDTO convertUsuarioToUsuarioDTO(Usuario usuario) {
		return new UsuarioDTO(usuario.getId(), usuario.getNome(), usuario.getUsername(), usuario.getAuthorities().stream().findFirst().get().getAuthority());

	}
}
