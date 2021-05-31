package br.com.softbank.consulta.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NewConsultaDTO {

	@JsonProperty("exame_id")
	@NotNull(message = "O id do exame é obrigatório")
	private Long exameId;
	@JsonProperty("laboratorio_id")
	@NotNull(message = "O id do laboratório é obrigatório")
	private long laboratorioId;
	
	public Long getExameId() {
		return exameId;
	}
	public void setExameId(Long exameId) {
		this.exameId = exameId;
	}
	public long getLaboratorioId() {
		return laboratorioId;
	}
	public void setLaboratorioId(long laboratorioId) {
		this.laboratorioId = laboratorioId;
	}
}
