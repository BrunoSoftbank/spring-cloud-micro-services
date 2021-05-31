package br.com.softbank.usuario.exception;

public class TokenInvalidException extends RuntimeException {

	private static final long serialVersionUID = 8125987210187005857L;
	
	private String message;
	
	public TokenInvalidException() {
		
	}
	
	public TokenInvalidException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
