package br.com.softbank.laboratorio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softbank.laboratorio.model.Endereco;
import br.com.softbank.laboratorio.repository.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Endereco findByCidadeAndBairroAndRuaAndNumero(String cidade, String bairro, String rua, String numero) {
		return enderecoRepository.findByCidadeIgnoreCaseAndBairroIgnoreCaseAndRuaIgnoreCaseAndNumeroIgnoreCase(cidade, bairro, rua, numero);
	}

	public Endereco save(Endereco endereço) {
		return enderecoRepository.save(endereço);
	}

}
