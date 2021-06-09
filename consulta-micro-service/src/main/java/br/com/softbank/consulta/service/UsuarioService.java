package br.com.softbank.consulta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softbank.consulta.enuns.ErrosDefaultEnum;
import br.com.softbank.consulta.exception.ConsultaNotFoundException;
import br.com.softbank.consulta.exception.GenericException;
import br.com.softbank.consulta.integration.Oauth2Integration;
import br.com.softbank.consulta.integration.UsuarioIntegration;
import br.com.softbank.consulta.response.UsuarioResponse;
import feign.FeignException;

@Service
public class UsuarioService {

	@Autowired
	private Oauth2Integration oauth2Integration;
	@Autowired
	private UsuarioIntegration usuarioIntegration;
	
	public UsuarioResponse getAuthenticatedUser(String Authorization) {
		try {
			return oauth2Integration.getAuthenticatedUser(Authorization);
		} catch (Exception e) {
			throw new GenericException(ErrosDefaultEnum.GENERIC_ERROR.getDescricao());
		}
	}
	
	public UsuarioResponse findById(String Authorization, Long id, Long consultaId) {
		try {
			return usuarioIntegration.findById(Authorization, id);
		} catch (Exception e) {
			if (e instanceof FeignException) {
				throw new ConsultaNotFoundException(String.format(ErrosDefaultEnum.CONSULTA_NAO_ENCONTRADA.getDescricao(), consultaId));
			}
			throw new GenericException(ErrosDefaultEnum.GENERIC_ERROR.getDescricao());
		}
	}
}
