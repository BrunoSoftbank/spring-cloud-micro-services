package br.com.softbank.usuario.exception;

public class UsuarioNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 8125987210187005857L;
	
	private String message;
	
	public UsuarioNotFoundException() {
		
	}
	
	public UsuarioNotFoundException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}