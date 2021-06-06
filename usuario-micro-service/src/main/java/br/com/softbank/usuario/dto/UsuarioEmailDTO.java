package br.com.softbank.usuario.dto;

import java.io.Serializable;

import com.google.gson.Gson;

public class UsuarioEmailDTO {
	
	private String nome;
	private String email;
	private String token;
	
	public UsuarioEmailDTO() {
		
	}
	
	public UsuarioEmailDTO(String nome, String email, String token) {
		this.nome = nome;
		this.email = email;
		this.token = token;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}		
}