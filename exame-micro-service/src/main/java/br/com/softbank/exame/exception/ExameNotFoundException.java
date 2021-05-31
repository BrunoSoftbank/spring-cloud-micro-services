package br.com.softbank.exame.exception;

public class ExameNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 183680519599487871L;

	private String message;
	
	public ExameNotFoundException() {
		
	}
	
	public ExameNotFoundException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}