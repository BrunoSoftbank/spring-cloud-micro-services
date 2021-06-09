package br.com.softbank.laboratorio.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.softbank.laboratorio.model.Laboratorio;
import br.com.softbank.laboratorio.request.LaboratorioRequest;
import br.com.softbank.laboratorio.response.LaboratorioResponse;

@Component
public class LaboratorioConverter {
	
	@Autowired
	private EnderecoConverter enderecoConverter;
	@Autowired
	private StatusConverter statusConverter;
	
	public LaboratorioResponse convertLaboratorioEntityToLaboratorioResponse(Laboratorio laboratorio) {
		return new LaboratorioResponse(laboratorio.getId(), laboratorio.getNome(), enderecoConverter.convertEnderecoEntityToEnderecoResponse(laboratorio.getEndereco()), statusConverter.convertStatusEntityToStatusResponse(laboratorio.getStatus()));
	}

	public Laboratorio convertLaboratorioRequestToLaboratorioEntity(LaboratorioRequest request) {
		return new Laboratorio(null, request.getNome(), enderecoConverter.convertEnderecoRequestToEnderecoEntity(request.getEndereco()), null);
	}

}
