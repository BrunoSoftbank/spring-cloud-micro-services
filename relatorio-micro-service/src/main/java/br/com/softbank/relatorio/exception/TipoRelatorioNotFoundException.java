package br.com.softbank.relatorio.exception;

public class TipoRelatorioNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 2954889151837788109L;
	
	private String message;
	
	public TipoRelatorioNotFoundException() {
		
	}
	
	public TipoRelatorioNotFoundException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

}
