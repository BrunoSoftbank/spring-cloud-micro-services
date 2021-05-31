package br.com.softbank.consulta.exception;

public class GenericException extends RuntimeException {

	private static final long serialVersionUID = 919488034215728067L;

	private String message;
	
	public GenericException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
