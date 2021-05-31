package br.com.softbank.laboratorio.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import br.com.softbank.laboratorio.model.Laboratorio;

public class NovoLaboratorioDTO {

	@NotBlank(message = "O nome do laboratório é obrigatório")
	private String nome;
	@Valid
	private EnderecoDTO endereco;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public EnderecoDTO getEndereco() {
		return endereco;
	}
	public void setEndereco(EnderecoDTO endereco) {
		this.endereco = endereco;
	}
	
	public Laboratorio convertToEntity() {
		return new Laboratorio(null, nome, endereco != null ? endereco.convertToEntity() : null, null);
	}
	
}
