package br.com.softbank.file.exception;

public class HeaderNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4055248107640845937L;
	
	private String message;

	public HeaderNotFoundException() {
		
	}
	
	public HeaderNotFoundException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}

