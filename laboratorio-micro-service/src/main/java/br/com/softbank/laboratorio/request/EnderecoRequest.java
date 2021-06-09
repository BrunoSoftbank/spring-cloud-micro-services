package br.com.softbank.laboratorio.request;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoRequest {

	@NotBlank(message = "A cidade é obrigatório")
	private String cidade;
	@NotBlank(message = "O bairro é obrigatório")
	private String bairro;
	@NotBlank(message = "A rua é obrigatório")
	private String rua;
	@NotBlank(message = "O número é obrigatório")
	private String numero;
}
