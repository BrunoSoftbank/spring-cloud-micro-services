package br.com.softbank.laboratorio.converter;

import org.springframework.stereotype.Component;

import br.com.softbank.laboratorio.model.Status;
import br.com.softbank.laboratorio.response.StatusResponse;

@Component
public class StatusConverter {
	
	public StatusResponse convertStatusEntityToStatusResponse(Status entity) {
		return new StatusResponse(entity.getId(), entity.getDescricao());
	}
	
	public Status convertStatusResponseToStatusEntity(StatusResponse statusResponse) {
		return new Status(statusResponse.getId(), statusResponse.getDescricao());
	}
}
