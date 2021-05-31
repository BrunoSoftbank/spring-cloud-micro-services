package br.com.softbank.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import br.com.softbank.batch.dto.EnderecoDTO;
import br.com.softbank.batch.dto.LaboratorioFromFileDTO;
import br.com.softbank.batch.dto.LaboratorioRequestDTO;

@Component
public class LaboratorioProcessor implements ItemProcessor<LaboratorioFromFileDTO, LaboratorioRequestDTO>{

	@Override
	public LaboratorioRequestDTO process(LaboratorioFromFileDTO item) throws Exception {
		LaboratorioRequestDTO request = new LaboratorioRequestDTO();
		request.setNome(item.getNome());
		EnderecoDTO endereco = new EnderecoDTO();
		endereco.setCidade(item.getCidade());
		endereco.setBairro(item.getBairro());
		endereco.setRua(item.getRua());
		endereco.setNumero(item.getNumero());
		request.setEndereco(endereco);
		return request;
	}
}
