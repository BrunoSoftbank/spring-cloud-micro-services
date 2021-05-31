package br.com.softbank.exame.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.softbank.exame.model.Exame;

public class AtualizarExameDTO {

	@NotNull(message = "O id do exame é obrigatório")
	private Long id;
	@NotBlank(message = "O nome do exame é obrigatório")
	private String nome;
	@Valid
	private TipoDTO tipo;
	
	
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
	public TipoDTO getTipo() {
		return tipo;
	}
	public void setTipo(TipoDTO tipo) {
		this.tipo = tipo;
	}
	
	public Exame convertToEntity() {
		return new Exame(id, nome, null, tipo != null ? tipo.convertToEntity() : null);
	}
}
