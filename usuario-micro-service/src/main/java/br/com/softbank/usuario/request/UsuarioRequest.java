package br.com.softbank.usuario.request;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequest {
	
	@NotBlank(message = "O nome do usuário é obrigatório")
	private String nome;
	@NotBlank(message = "O email do usuário é obrigatório")
	private String email;
	@NotBlank(message = "A senha do usuário é obrigatória")
	private String senha;

}
