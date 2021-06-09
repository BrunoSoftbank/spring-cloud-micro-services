package br.com.softbank.batch.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoRequest {

	@JsonProperty("cidade")
	private String cidade;
	@JsonProperty("bairro")
	private String bairro;
	@JsonProperty("rua")
	private String rua;
	@JsonProperty("numero")
	private String numero;
}
