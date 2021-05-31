package br.com.softbank.consultawebservice.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.CLIENT)
public class ConsultaAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 183680519599487871L;

	private String message;

	public ConsultaAlreadyExistsException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
