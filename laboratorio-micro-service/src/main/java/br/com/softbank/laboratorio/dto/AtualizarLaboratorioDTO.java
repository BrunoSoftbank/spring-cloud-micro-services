package br.com.softbank.laboratorio.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.softbank.laboratorio.model.Laboratorio;

public class AtualizarLaboratorioDTO {

	@NotNull(message = "O id do exame é obrigatório")
	private Long id;
	@NotBlank(message = "O nome do laboratório é obrigatório")
	private String nome;
	@Valid
	private EnderecoDTO endereco;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	
	public Laboratorio convertToEntity() {
		return new Laboratorio(id, nome, endereco != null ? endereco.convertToEntity() : null, null);
	}
	
}
