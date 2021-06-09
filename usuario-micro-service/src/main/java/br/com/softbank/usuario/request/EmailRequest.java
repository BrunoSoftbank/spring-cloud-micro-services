package br.com.softbank.usuario.request;

import com.google.gson.Gson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailRequest {
	
	private String nome;
	private String email;
	private String token;
	
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}		
}
