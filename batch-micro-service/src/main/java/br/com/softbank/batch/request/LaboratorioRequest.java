package br.com.softbank.batch.request;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LaboratorioRequest {

	@JsonProperty("nome")
	private String nome;
	@Valid
	@JsonProperty("endereco")
	private EnderecoRequest endereco;
}
