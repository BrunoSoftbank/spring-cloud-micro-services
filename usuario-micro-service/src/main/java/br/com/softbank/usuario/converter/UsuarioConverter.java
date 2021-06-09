package br.com.softbank.usuario.converter;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import br.com.softbank.usuario.model.Usuario;
import br.com.softbank.usuario.request.UsuarioRequest;
import br.com.softbank.usuario.response.UsuarioResponse;

@Component
public class UsuarioConverter {
	
	public Usuario convertUsuarioRequestToUsuarioEntity(UsuarioRequest request) {
		return new Usuario(request.getNome(), request.getEmail(), request.getSenha(), LocalDate.now());
	}
	
	public UsuarioResponse convertUsuarioEntityToUsuarioResponse(Usuario entity) {
		return new UsuarioResponse(entity.getId(), entity.getNome(), entity.getEmail(), entity.getIsAtivo(), entity.getRoles().get(0).getNome(), entity.getDataCadastro());
	}

}
