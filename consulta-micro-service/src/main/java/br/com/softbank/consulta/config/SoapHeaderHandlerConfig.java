package br.com.softbank.consulta.config;

import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SoapHeaderHandlerConfig implements SOAPHandler<SOAPMessageContext> {
	
	@Value("${USUARIO_WEB_SERVICE}")
	private String usuarioWebService;
	@Value("${SENHA_WEB_SERVICE}")
	private String senhaWebService;

	@Override
	public void close(MessageContext messageContext) {
		
	}

	@Override
	public boolean handleFault(SOAPMessageContext sOAPMessageContext) {
		return true;
	}

	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		
		Boolean outboundProperty = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		if(outboundProperty.booleanValue()) {
			try {
				SOAPEnvelope envelope = context.getMessage().getSOAPPart().getEnvelope();
				SOAPHeader header = envelope.getHeader();
				
				SOAPElement security = header.addChildElement("Security", "wsse", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
				SOAPElement userNameToken = security.addChildElement("UsernameToken", "wsse");
				
				SOAPElement usuario = userNameToken.addChildElement("Username", "wsse");
				usuario.addTextNode(usuarioWebService);
				
				SOAPElement senha = userNameToken.addChildElement("Password", "wsse");
				senha.addTextNode(senhaWebService);
				
			} catch (SOAPException e) {
				e.printStackTrace();
			}
		}
		
		return outboundProperty;
	}

	@Override
	public Set<QName> getHeaders() {
		return null;
	}

}
