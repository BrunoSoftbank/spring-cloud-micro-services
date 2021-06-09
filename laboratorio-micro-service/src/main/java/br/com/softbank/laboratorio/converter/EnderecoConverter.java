package br.com.softbank.laboratorio.converter;

import org.springframework.stereotype.Component;

import br.com.softbank.laboratorio.model.Endereco;
import br.com.softbank.laboratorio.request.EnderecoRequest;
import br.com.softbank.laboratorio.response.EnderecoResponse;

@Component
public class EnderecoConverter {
	
	public EnderecoResponse convertEnderecoEntityToEnderecoResponse(Endereco endereco) {
		return new EnderecoResponse(endereco.getCidade(), endereco.getBairro(), endereco.getRua(), endereco.getNumero());
	}

	public Endereco convertEnderecoRequestToEnderecoEntity(EnderecoRequest enderecoRequest) {
		return new Endereco(null, enderecoRequest.getCidade(), enderecoRequest.getBairro(), enderecoRequest.getRua(), enderecoRequest.getNumero());
	}
}
