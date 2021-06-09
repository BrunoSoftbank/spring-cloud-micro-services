package br.com.softbank.exame.request;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoRequest {

	@NotNull(message = "O id do tipo de exame é obrigatório")
	private Long id;

}
