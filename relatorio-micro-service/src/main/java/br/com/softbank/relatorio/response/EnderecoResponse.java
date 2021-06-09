package br.com.softbank.relatorio.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoResponse {

	private String cidade;
	private String bairro;
	private String rua;
	private String numero;
}
