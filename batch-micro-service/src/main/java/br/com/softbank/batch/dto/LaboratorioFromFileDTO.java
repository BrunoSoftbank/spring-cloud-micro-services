package br.com.softbank.batch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LaboratorioFromFileDTO {

	private String nome;
	private String cidade;
	private String bairro;
	private String rua;
	private String numero;
	
}
