package br.com.softbank.laboratorio.request;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LaboratorioRequest {

	@NotBlank(message = "O nome do laboratório é obrigatório")
	private String nome;
	@Valid
	@NotNull(message = "O endereço é obrigatório")
	private EnderecoRequest endereco;
}
