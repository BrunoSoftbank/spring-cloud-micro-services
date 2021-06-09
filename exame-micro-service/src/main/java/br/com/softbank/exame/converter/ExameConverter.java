package br.com.softbank.exame.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.softbank.exame.model.Exame;
import br.com.softbank.exame.request.ExameRequest;
import br.com.softbank.exame.response.ExameResponse;

@Component
public class ExameConverter {
	
	@Autowired
	private StatusConverter statusConverter;
	@Autowired
	private TipoConverter tipoConverter;
	
	public ExameResponse convertExameEntityToExameResponse(Exame entity) {
		return new ExameResponse(entity.getId(), entity.getNome(), statusConverter.convertStatusEntityToStatusResponse(entity.getStatus()), tipoConverter.convertTipoEntityToTipoResponse(entity.getTipo()));
	}
	
	public Exame convertExameResponseToExameEntity(ExameResponse exameResponse) {
		return new Exame(exameResponse.getId(), exameResponse.getNome(), statusConverter.convertStatusResponseToStatusEntity(exameResponse.getStatus()), tipoConverter.convertTipoResponseToTipoEntity(exameResponse.getTipo()));
	}
	
	public Exame convertExameRequestToExameEntity(ExameRequest request) {
		return new Exame(null, request.getNome(), null, tipoConverter.convertTipoRequestToTipoEntity(request.getTipo()));
	}
}
