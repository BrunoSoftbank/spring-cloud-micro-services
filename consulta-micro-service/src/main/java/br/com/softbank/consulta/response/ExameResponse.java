package br.com.softbank.consulta.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExameResponse {

	private Long id;
	private String nome;
	private TipoResponse tipo;
}
