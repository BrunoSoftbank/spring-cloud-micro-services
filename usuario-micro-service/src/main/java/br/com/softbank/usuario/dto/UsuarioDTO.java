package br.com.softbank.usuario.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
	
	@NotBlank(message = "O nome do usuário é obrigatório")
	private String nome;
	@NotBlank(message = "O email do usuário é obrigatório")
	private String email;
	@NotBlank(message = "A senha do usuário é obrigatória")
	private String senha;

}
