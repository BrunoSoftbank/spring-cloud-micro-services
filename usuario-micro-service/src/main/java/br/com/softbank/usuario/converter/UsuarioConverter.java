package br.com.softbank.usuario.converter;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import br.com.softbank.usuario.dto.UsuarioDTO;
import br.com.softbank.usuario.model.Usuario;
import br.com.softbank.usuario.response.UsuarioResponse;

@Component
public class UsuarioConverter {
	
	public Usuario convertUsuarioDTOToEntity(UsuarioDTO usuarioDTO) {
		return new Usuario(usuarioDTO.getNome(), usuarioDTO.getEmail(), usuarioDTO.getSenha(), LocalDate.now());
	}
	
	public UsuarioResponse convertUsuarioToUsuarioResponse(Usuario usuario) {
		return new UsuarioResponse(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getIsAtivo(), usuario.getRoles().get(0).getNome(), usuario.getDataCadastro());
	}

}
