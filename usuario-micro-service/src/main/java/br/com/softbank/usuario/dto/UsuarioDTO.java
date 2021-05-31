package br.com.softbank.usuario.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import br.com.softbank.usuario.model.Usuario;

public class UsuarioDTO {
	
	@NotBlank(message = "O nome do usuário é obrigatório")
	private String nome;
	@NotBlank(message = "O email do usuário é obrigatório")
	private String email;
	@NotBlank(message = "A senha do usuário é obrigatória")
	private String senha;

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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Usuario convertToEntity() {
		return new Usuario(nome, email, senha, LocalDate.now());
	}

}
