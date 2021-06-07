package br.com.softbank.email.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEmailDTO {

	private String nome;
	private String email;
	private String token;
	
}