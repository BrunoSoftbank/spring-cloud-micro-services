package br.com.softbank.exame.dto;

import javax.validation.constraints.NotNull;

import br.com.softbank.exame.model.Tipo;

public class TipoDTO {

	@NotNull(message = "O id do tipo de exame é obrigatório")
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Tipo convertToEntity() {
		return new Tipo(id, null);
	}
}
