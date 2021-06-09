package br.com.softbank.relatorio.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LaboratorioResponse {

	private Long id;
	private String nome;
	private EnderecoResponse endereco;
	private StatusResponse status;
}
