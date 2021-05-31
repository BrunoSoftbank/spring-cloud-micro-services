package br.com.softbank.consulta.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softbank.consulta.config.SoapHeaderHandlerResolverImpl;
import br.com.softbank.consulta.dto.ConsultaDTO;
import br.com.softbank.consulta.dto.ExameDTO;
import br.com.softbank.consulta.dto.LaboratorioDTO;
import br.com.softbank.consulta.dto.NewConsultaDTO;
import br.com.softbank.consulta.dto.UsuarioDTO;
import br.com.softbank.consulta.enuns.ErrosDefaultEnum;
import br.com.softbank.consulta.exception.ConsultaAlreadyExistsException;
import br.com.softbank.consulta.exception.ConsultaNotFoundException;
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

	public ConsultaDTO save(String Authorization, NewConsultaDTO newConsultaDTO) {
		UsuarioDTO usuarioDTO = usuarioService.getAuthenticatedUser(Authorization);
		ExameDTO exameDTO = exameService.findById(Authorization, newConsultaDTO.getExameId());
		LaboratorioDTO laboratorioDTO = laboratorioService.findById(Authorization, newConsultaDTO.getLaboratorioId());
		
		this.verificaSeJaExisteConsultaAgendada(usuarioDTO, exameDTO, laboratorioDTO);

		SalvarConsultaRequest salvarConsultaRequest = new SalvarConsultaRequest();
		ConsultaRequest request = new ConsultaRequest();
		request.setUsuarioId(Math.toIntExact(usuarioDTO.getId()));
		request.setExameId(Math.toIntExact(exameDTO.getId()));
		request.setLaboratorioId(Math.toIntExact(laboratorioDTO.getId()));
		salvarConsultaRequest.setConsultaRequest(request);
		
		SalvarConsultaResponse response = port.salvarConsulta(salvarConsultaRequest);

		return new ConsultaDTO(Long.valueOf(response.getConsultaResponse().getId()), usuarioDTO, exameDTO, laboratorioDTO);
	}

	public List<ConsultaDTO> findAll(String Authorization) {
		List<ConsultaDTO> consultas = new ArrayList<>();
		
		UsuarioDTO usuarioLogado = usuarioService.getAuthenticatedUser(Authorization);
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
	public ConsultaDTO findById(String Authorization, Long id) {
		ConsultaDTO consultaDTO = null;
		
		UsuarioDTO usuarioLogado = usuarioService.getAuthenticatedUser(Authorization);
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
	private void verificaSeJaExisteConsultaAgendada(UsuarioDTO usuarioDTO, ExameDTO exameDTO, LaboratorioDTO laboratorioDTO) {		
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
	
	private ConsultaDTO convertConsultaResponseToEntity(String Authorization, ConsultaResponse response) {
		UsuarioDTO usuarioDTO = usuarioService.findById(Authorization, Long.valueOf(response.getUsuarioId()), Long.valueOf(response.getId()));
		ExameDTO exameDTO = exameService.findById(Authorization, Long.valueOf(response.getExameId()));
		LaboratorioDTO laboratorioDTO = laboratorioService.findById(Authorization, Long.valueOf(response.getLaboratorioId()));	
		return new ConsultaDTO(Long.valueOf(response.getId()), usuarioDTO, exameDTO, laboratorioDTO);
	}
}
