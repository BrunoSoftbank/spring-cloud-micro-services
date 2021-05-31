package br.com.softbank.batch.dto;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;


public class LaboratorioRequestDTO {

	@JsonProperty("nome")
	private String nome;
	@Valid
	@JsonProperty("endereco")
	private EnderecoDTO endereco;
	
	public LaboratorioRequestDTO() {
		
	}

	public LaboratorioRequestDTO(String nome, @Valid EnderecoDTO endereco) {
		super();
		this.nome = nome;
		this.endereco = endereco;
	}

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

	@Override
	public String toString() {
		return "LaboratorioRequestDTO [nome=" + nome + ", endereco=" + endereco + "]";
	}
}
