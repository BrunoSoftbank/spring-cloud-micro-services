package br.com.softbank.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import br.com.softbank.batch.dto.LaboratorioFromFileDTO;
import br.com.softbank.batch.request.EnderecoRequest;
import br.com.softbank.batch.request.LaboratorioRequest;

@Component
public class LaboratorioProcessor implements ItemProcessor<LaboratorioFromFileDTO, LaboratorioRequest>{

	@Override
	public LaboratorioRequest process(LaboratorioFromFileDTO item) throws Exception {
		LaboratorioRequest request = new LaboratorioRequest();
		request.setNome(item.getNome());
		EnderecoRequest endereco = new EnderecoRequest();
		endereco.setCidade(item.getCidade());
		endereco.setBairro(item.getBairro());
		endereco.setRua(item.getRua());
		endereco.setNumero(item.getNumero());
		request.setEndereco(endereco);
		return request;
	}
}
