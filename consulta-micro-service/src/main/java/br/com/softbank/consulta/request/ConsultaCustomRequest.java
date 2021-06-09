package br.com.softbank.consulta.request;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaCustomRequest {

	@JsonProperty("exame_id")
	@NotNull(message = "O id do exame é obrigatório")
	private Long exameId;
	@JsonProperty("laboratorio_id")
	@NotNull(message = "O id do laboratório é obrigatório")
	private long laboratorioId;
}
