package br.com.softbank.usuario.exception;

public class UsuarioAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 5119466394756323144L;
	
	public UsuarioAlreadyExistsException(String message) {
		this.message = message;
	}

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
