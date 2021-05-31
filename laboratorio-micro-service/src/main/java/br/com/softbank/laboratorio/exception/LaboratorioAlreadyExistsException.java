package br.com.softbank.laboratorio.exception;

public class LaboratorioAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 183680519599487871L;

	private String message;
	
	public LaboratorioAlreadyExistsException() {
		
	}
	
	public LaboratorioAlreadyExistsException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
