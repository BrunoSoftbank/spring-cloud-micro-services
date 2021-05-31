package br.com.softbank.laboratorio.exception;

public class LaboratorioNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 183680519599487871L;

	private String message;
	
	public LaboratorioNotFoundException() {
		
	}
	
	public LaboratorioNotFoundException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}