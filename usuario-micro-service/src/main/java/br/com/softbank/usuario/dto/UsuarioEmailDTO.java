package br.com.softbank.usuario.dto;

import com.google.gson.Gson;

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
	
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}		
}
