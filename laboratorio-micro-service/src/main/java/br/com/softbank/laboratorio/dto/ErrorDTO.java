package br.com.softbank.laboratorio.dto;

public class ErrorDTO {

	private String message;

	public ErrorDTO() {
		
	}
	
	public ErrorDTO(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ErrorDTO [message=" + message + "]";
	}
}
