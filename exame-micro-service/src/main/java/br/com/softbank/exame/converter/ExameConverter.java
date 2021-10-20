package br.com.softbank.exame.converter;

import org.springframework.stereotype.Component;

import br.com.softbank.exame.model.Exame;
import br.com.softbank.exame.request.ExameRequest;
import br.com.softbank.exame.response.ExameResponse;

@Component
public class ExameConverter {
	
	public ExameResponse convertExameEntityToExameResponse(Exame entity) {
		return new ExameResponse(entity.getId(), entity.getNome(), entity.getDescricao());
	}
	
	public Exame convertExameResponseToExameEntity(ExameResponse exameResponse) {
		return new Exame(exameResponse.getId(), exameResponse.getNome(), exameResponse.getDescricao());
	}
	
	public Exame convertExameRequestToExameEntity(ExameRequest request) {
		return new Exame(null, request.getNome(), request.getDescricao());
	}
}
