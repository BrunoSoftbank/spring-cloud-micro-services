package br.com.softbank.exame.exception;

public class TipoExameNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 183680519599487871L;

	private String message;
	
	public TipoExameNotFoundException() {
		
	}
	
	public TipoExameNotFoundException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}