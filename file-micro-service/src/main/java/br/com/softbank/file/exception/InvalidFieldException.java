package br.com.softbank.file.exception;

public class InvalidFieldException extends RuntimeException {

	private static final long serialVersionUID = 4055248107640845937L;
	
	private String message;

	public InvalidFieldException() {
		
	}
	
	public InvalidFieldException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}

