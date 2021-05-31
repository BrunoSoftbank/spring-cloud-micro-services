package br.com.softbank.consulta.exception;

public class ConsultaAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 183680519599487871L;

	private String message;

	public ConsultaAlreadyExistsException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
