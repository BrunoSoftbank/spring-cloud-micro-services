package br.com.softbank.exame.converter;

import org.springframework.stereotype.Component;

import br.com.softbank.exame.model.Tipo;
import br.com.softbank.exame.request.TipoRequest;
import br.com.softbank.exame.response.TipoResponse;

@Component
public class TipoConverter {

	public TipoResponse convertTipoEntityToTipoResponse(Tipo entity) {
		return new TipoResponse(entity.getId(), entity.getDescricao());
	}
	
	public Tipo convertTipoResponseToTipoEntity(TipoResponse tipoResponse) {
		return new Tipo(tipoResponse.getId(), tipoResponse.getDescricao());
	}
	
	public Tipo convertTipoRequestToTipoEntity(TipoRequest tipoRequest) {
		return new Tipo(tipoRequest.getId(), null);
	}
}
