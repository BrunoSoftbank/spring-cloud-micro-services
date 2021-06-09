package br.com.softbank.consulta.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softbank.consulta.config.SoapHeaderHandlerResolverImpl;
import br.com.softbank.consulta.enuns.ErrosDefaultEnum;
import br.com.softbank.consulta.exception.ConsultaAlreadyExistsException;
import br.com.softbank.consulta.exception.ConsultaNotFoundException;
import br.com.softbank.consulta.request.ConsultaCustomRequest;
import br.com.softbank.consulta.response.ConsultaCustomResponse;
import br.com.softbank.consulta.response.ExameResponse;
import br.com.softbank.consulta.response.LaboratorioResponse;
import br.com.softbank.consulta.response.UsuarioResponse;
import br.com.softbank.consulta.soap.ConsultaPort;
import br.com.softbank.consulta.soap.ConsultaPortService;
import br.com.softbank.consulta.soap.ConsultaRequest;
import br.com.softbank.consulta.soap.ConsultaResponse;
import br.com.softbank.consulta.soap.DeleteConsultaRequest;
import br.com.softbank.consulta.soap.FindAllConsultaRequest;
import br.com.softbank.consulta.soap.FindAllConsultaResponse;
import br.com.softbank.consulta.soap.FindConsultaByFiltersRequest;
import br.com.softbank.consulta.soap.FindConsultaByIdRequest;
import br.com.softbank.consulta.soap.FindConsultaByIdResponse;
import br.com.softbank.consulta.soap.SalvarConsultaRequest;
import br.com.softbank.consulta.soap.SalvarConsultaResponse;

@Service
public class ConsultaService {
	
	private ConsultaPortService consultaPortService;
	private ConsultaPort port;

	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private ExameService exameService;
	@Autowired
	private LaboratorioService laboratorioService;
	@Autowired
	private SoapHeaderHandlerResolverImpl soapHeaderHandlerResolverImpl;
	
	@PostConstruct
	public void init() {
		consultaPortService = new ConsultaPortService(); 
		consultaPortService.setHandlerResolver(soapHeaderHandlerResolverImpl);
		port = consultaPortService.getConsultaPortSoap11();
	}

	public ConsultaCustomResponse save(String Authorization, ConsultaCustomRequest newConsultaDTO) {
		UsuarioResponse usuarioDTO = usuarioService.getAuthenticatedUser(Authorization);
		ExameResponse exameDTO = exameService.findById(Authorization, newConsultaDTO.getExameId());
		LaboratorioResponse laboratorioDTO = laboratorioService.findById(Authorization, newConsultaDTO.getLaboratorioId());
		
		this.verificaSeJaExisteConsultaAgendada(usuarioDTO, exameDTO, laboratorioDTO);

		SalvarConsultaRequest salvarConsultaRequest = new SalvarConsultaRequest();
		ConsultaRequest request = new ConsultaRequest();
		request.setUsuarioId(Math.toIntExact(usuarioDTO.getId()));
		request.setExameId(Math.toIntExact(exameDTO.getId()));
		request.setLaboratorioId(Math.toIntExact(laboratorioDTO.getId()));
		salvarConsultaRequest.setConsultaRequest(request);
		
		SalvarConsultaResponse response = port.salvarConsulta(salvarConsultaRequest);

		return new ConsultaCustomResponse(Long.valueOf(response.getConsultaResponse().getId()), usuarioDTO, exameDTO, laboratorioDTO);
	}

	public List<ConsultaCustomResponse> findAll(String Authorization) {
		List<ConsultaCustomResponse> consultas = new ArrayList<>();
		
		UsuarioResponse usuarioLogado = usuarioService.getAuthenticatedUser(Authorization);
		FindAllConsultaRequest request = new FindAllConsultaRequest();	
		FindAllConsultaResponse response = port.findAllConsulta(request);
		
		response.getConsultaResponse().forEach(consulta -> {
			if(usuarioLogado.getPerfil().equalsIgnoreCase("ROLE_ADMIN")) {
				consultas.add(this.convertConsultaResponseToEntity(Authorization, consulta));
			} else if(usuarioLogado.getPerfil().equalsIgnoreCase("ROLE_USER") && consulta.getUsuarioId() == usuarioLogado.getId()) {
				consultas.add(this.convertConsultaResponseToEntity(Authorization, consulta));
			}		
		});
		return consultas;
	}

	@SuppressWarnings("restriction")
	public ConsultaCustomResponse findById(String Authorization, Long id) {
		ConsultaCustomResponse consultaDTO = null;
		
		UsuarioResponse usuarioLogado = usuarioService.getAuthenticatedUser(Authorization);
		FindConsultaByIdRequest request = new FindConsultaByIdRequest();
		request.setId(Math.toIntExact(id));
		
		try {
			FindConsultaByIdResponse response = port.findConsultaById(request);
			consultaDTO = this.convertConsultaResponseToEntity(Authorization, response.getConsultaResponse());
		} catch (com.sun.xml.internal.ws.fault.ServerSOAPFaultException e) {
			throw new ConsultaNotFoundException(String.format(ErrosDefaultEnum.CONSULTA_NAO_ENCONTRADA.getDescricao(), id));
		}
		
		if(usuarioLogado.getPerfil().equalsIgnoreCase("ROLE_ADMIN")) {
			return consultaDTO;
		} else if(usuarioLogado.getPerfil().equalsIgnoreCase("ROLE_USER") && consultaDTO.getUsuarioDTO().getId() == usuarioLogado.getId()) {
			return consultaDTO;
		}
		throw new ConsultaNotFoundException(String.format(ErrosDefaultEnum.CONSULTA_NAO_ENCONTRADA.getDescricao(), id));
	}

	public void delete(String Authorization, Long id) {
		this.findById(Authorization, id);
		
		DeleteConsultaRequest deleteConsultaRequest = new DeleteConsultaRequest();
		deleteConsultaRequest.setId(Math.toIntExact(id));
		port.deleteConsulta(deleteConsultaRequest);
	}
	
	// Se ja existir consulta lança exceção, caso contrário cria uma nova
	@SuppressWarnings("restriction")
	private void verificaSeJaExisteConsultaAgendada(UsuarioResponse usuarioDTO, ExameResponse exameDTO, LaboratorioResponse laboratorioDTO) {		
		FindConsultaByFiltersRequest findConsultaByFiltersRequest = new FindConsultaByFiltersRequest();
		ConsultaRequest request = new ConsultaRequest();
		request.setUsuarioId(Math.toIntExact(usuarioDTO.getId()));
		request.setExameId(Math.toIntExact(exameDTO.getId()));
		request.setLaboratorioId(Math.toIntExact(laboratorioDTO.getId()));
		findConsultaByFiltersRequest.setConsultaRequest(request);
		
		try {
			port.findConsultaByFilters(findConsultaByFiltersRequest);		
			throw new ConsultaAlreadyExistsException(String.format(ErrosDefaultEnum.CONSULTA_JA_EXISTENTE.getDescricao(), usuarioDTO.getNome(), exameDTO.getNome(), laboratorioDTO.getNome()));
		} catch (com.sun.xml.internal.ws.fault.ServerSOAPFaultException e) {
			return;
		}		
	}
	
	private ConsultaCustomResponse convertConsultaResponseToEntity(String Authorization, ConsultaResponse response) {
		UsuarioResponse usuarioDTO = usuarioService.findById(Authorization, Long.valueOf(response.getUsuarioId()), Long.valueOf(response.getId()));
		ExameResponse exameDTO = exameService.findById(Authorization, Long.valueOf(response.getExameId()));
		LaboratorioResponse laboratorioDTO = laboratorioService.findById(Authorization, Long.valueOf(response.getLaboratorioId()));	
		return new ConsultaCustomResponse(Long.valueOf(response.getId()), usuarioDTO, exameDTO, laboratorioDTO);
	}
}
