package br.com.softbank.consulta.config;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SoapHeaderHandlerResolverImpl implements HandlerResolver {
	
	@Autowired
	private SoapHeaderHandlerConfig headerHandlerConfig;

	@Override
	public List<Handler> getHandlerChain(PortInfo portInfo) {
		List<Handler> handlerChain = new ArrayList<Handler>();		
		handlerChain.add(headerHandlerConfig);		
		return handlerChain;
	}
}
