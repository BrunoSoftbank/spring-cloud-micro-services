package br.com.softbank.oauth2.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponse {

	private Long id;
	private String nome;
	private String email;
	private String perfil;

}
