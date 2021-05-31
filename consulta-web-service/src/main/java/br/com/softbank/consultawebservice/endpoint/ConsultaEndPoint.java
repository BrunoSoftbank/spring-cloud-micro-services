package br.com.softbank.consultawebservice.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import br.com.softbank.consultawebservice.service.ConsultaService;
import br.com.softbank.consultawebservice.soap.DeleteConsultaRequest;
import br.com.softbank.consultawebservice.soap.DeleteConsultaResponse;
import br.com.softbank.consultawebservice.soap.FindAllConsultaRequest;
import br.com.softbank.consultawebservice.soap.FindAllConsultaResponse;
import br.com.softbank.consultawebservice.soap.FindConsultaByFiltersRequest;
import br.com.softbank.consultawebservice.soap.FindConsultaByFiltersResponse;
import br.com.softbank.consultawebservice.soap.FindConsultaByIdRequest;
import br.com.softbank.consultawebservice.soap.FindConsultaByIdResponse;
import br.com.softbank.consultawebservice.soap.SalvarConsultaRequest;
import br.com.softbank.consultawebservice.soap.SalvarConsultaResponse;

@Endpoint
public class ConsultaEndPoint {
	
	@Autowired
	private ConsultaService consultaService;

	@PayloadRoot(namespace = "http://softbank.com.br", localPart = "SalvarConsultaRequest")
	@ResponsePayload
	public SalvarConsultaResponse salvar(@RequestPayload SalvarConsultaRequest request) throws Exception {
		return consultaService.salvar(request);
	}
	
	@PayloadRoot(namespace = "http://softbank.com.br", localPart = "FindConsultaByIdRequest")
	@ResponsePayload
	public FindConsultaByIdResponse findById(@RequestPayload FindConsultaByIdRequest request) {
		return consultaService.findById(request);
	}
	
	@PayloadRoot(namespace = "http://softbank.com.br", localPart = "FindConsultaByFiltersRequest")
	@ResponsePayload
	public FindConsultaByFiltersResponse findByFilters(@RequestPayload FindConsultaByFiltersRequest request) {
		return consultaService.findByFilters(request);
	}
	
	@PayloadRoot(namespace = "http://softbank.com.br", localPart = "FindAllConsultaRequest")
	@ResponsePayload
	public FindAllConsultaResponse findByFilters(@RequestPayload FindAllConsultaRequest request) throws Exception {
		return consultaService.findAll(request);
	}
	
	@PayloadRoot(namespace = "http://softbank.com.br", localPart = "DeleteConsultaRequest")
	@ResponsePayload
	public DeleteConsultaResponse delete(@RequestPayload DeleteConsultaRequest request) throws Exception {
		return consultaService.delete(request);
	}
}
