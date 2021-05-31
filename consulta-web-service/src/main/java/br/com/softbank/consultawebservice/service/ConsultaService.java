package br.com.softbank.consultawebservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softbank.consultawebservice.dao.ConsultaDAO;
import br.com.softbank.consultawebservice.enuns.ErrosDefaultEnum;
import br.com.softbank.consultawebservice.exception.ConsultaAlreadyExistsException;
import br.com.softbank.consultawebservice.exception.ConsultaNotFoundException;
import br.com.softbank.consultawebservice.model.Consulta;
import br.com.softbank.consultawebservice.soap.ConsultaResponse;
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
import br.com.softbank.consultawebservice.soap.Status;

@Service
public class ConsultaService {
	
	@Autowired
	private ConsultaDAO consultaDAO;

	public SalvarConsultaResponse salvar(SalvarConsultaRequest request) throws Exception {
		SalvarConsultaResponse salvarConsultaResponse = new SalvarConsultaResponse();
		
		Consulta consulta = consultaDAO.salvar(request.getConsultaRequest().getUsuarioId(), request.getConsultaRequest().getExameId(), request.getConsultaRequest().getLaboratorioId());
		
		ConsultaResponse consultaResponse = this.convertToConsultaResponse(consulta);
		salvarConsultaResponse.setConsultaResponse(consultaResponse);
		
		return salvarConsultaResponse;
	}
	
	public FindConsultaByIdResponse findById(FindConsultaByIdRequest request) {
		FindConsultaByIdResponse findConsultaByIdResponse = new FindConsultaByIdResponse();
		
		Consulta consulta = consultaDAO.findById(request.getId());
		if(consulta == null) {
			throw new ConsultaNotFoundException(String.format(ErrosDefaultEnum.CONSULTA_BY_ID_NAO_ENCONTRADA.getDescricao(), request.getId()));
		}
		
		ConsultaResponse consultaResponse = this.convertToConsultaResponse(consulta);
		findConsultaByIdResponse.setConsultaResponse(consultaResponse);
		
		return findConsultaByIdResponse;
	}
	
	public FindConsultaByFiltersResponse findByFilters(FindConsultaByFiltersRequest request) {
		FindConsultaByFiltersResponse findConsultaByFiltersResponse = new FindConsultaByFiltersResponse();
		
		Consulta consulta = consultaDAO.findByFilters(request.getConsultaRequest().getUsuarioId(), request.getConsultaRequest().getExameId(), request.getConsultaRequest().getLaboratorioId());
		if(consulta == null) {
			throw new ConsultaAlreadyExistsException(String.format(ErrosDefaultEnum.CONSULTA_BY_FILTERS_NAO_ENCONTRADA.getDescricao(), request.getConsultaRequest().getUsuarioId(), request.getConsultaRequest().getExameId(), request.getConsultaRequest().getLaboratorioId()));
		}
		
		ConsultaResponse consultaResponse = this.convertToConsultaResponse(consulta);
		findConsultaByFiltersResponse.setConsultaResponse(consultaResponse);
		
		return findConsultaByFiltersResponse;
	}
	
	public FindAllConsultaResponse findAll(FindAllConsultaRequest request) throws Exception {		
		FindAllConsultaResponse findAllConsultaResponse = new FindAllConsultaResponse();
		
		List<Consulta> consultas = consultaDAO.findAll();	
		consultas.stream().forEach(c -> {
			findAllConsultaResponse.getConsultaResponse().add(this.convertToConsultaResponse(c));
		});
		
		return findAllConsultaResponse;
	}
	
	public DeleteConsultaResponse delete(DeleteConsultaRequest request) throws Exception {
		DeleteConsultaResponse deleteConsultaResponse = new DeleteConsultaResponse();		
		Status status = Status.SUCCESS;
		
		FindConsultaByIdRequest requestId = new FindConsultaByIdRequest();
		requestId.setId(request.getId());
		
		this.findById(requestId);
		consultaDAO.delete(request.getId());
			
		deleteConsultaResponse.setStatus(status);
		return deleteConsultaResponse;
	}
	
	private ConsultaResponse convertToConsultaResponse(Consulta consulta) {
		ConsultaResponse response = new ConsultaResponse();
		response.setId(consulta.getId());
		response.setUsuarioId(consulta.getUsuarioId());
		response.setExameId(consulta.getExameId());
		response.setLaboratorioId(consulta.getLaboratorioId());		
		return response;
	}
}
