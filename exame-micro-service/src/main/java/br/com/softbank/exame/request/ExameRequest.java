package br.com.softbank.exame.request;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExameRequest {

	@NotBlank(message = "O nome do exame é obrigatório")
	private String nome;
	@Valid
	@NotNull(message = "O tipo é obrigatório")
	private TipoRequest tipo;
}
