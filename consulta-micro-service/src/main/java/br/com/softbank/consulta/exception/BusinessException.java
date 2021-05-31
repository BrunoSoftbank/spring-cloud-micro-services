package br.com.softbank.consulta.exception;

import br.com.softbank.consulta.dto.ResponseDTO;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 919488034215728067L;

	private ResponseDTO response;

	public BusinessException(ResponseDTO response) {
		this.response = response;
	}

	public ResponseDTO getResponseDTO() {
		return response;
	}
}
