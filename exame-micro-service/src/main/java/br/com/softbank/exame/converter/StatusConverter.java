package br.com.softbank.exame.converter;

import org.springframework.stereotype.Component;

import br.com.softbank.exame.model.Status;
import br.com.softbank.exame.response.StatusResponse;

@Component
public class StatusConverter {
	
	public StatusResponse convertStatusEntityToStatusResponse(Status entity) {
		return new StatusResponse(entity.getId(), entity.getDescricao());
	}
	
	public Status convertStatusResponseToStatusEntity(StatusResponse statusResponse) {
		return new Status(statusResponse.getId(), statusResponse.getDescricao());
	}
}
