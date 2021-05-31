package br.com.softbank.exame.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import br.com.softbank.exame.model.Exame;


public class NovoExameDTO {

	@NotBlank(message = "O nome do exame é obrigatório")
	private String nome;
	@Valid
	private TipoDTO tipo;
	
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
		return new Exame(null, nome, null, tipo != null ? tipo.convertToEntity() : null);
	}
	
}
