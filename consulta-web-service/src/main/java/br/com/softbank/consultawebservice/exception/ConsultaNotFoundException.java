package br.com.softbank.consultawebservice.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.CLIENT)
public class ConsultaNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 919488034215728067L;

	private String message;
	
	public ConsultaNotFoundException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
