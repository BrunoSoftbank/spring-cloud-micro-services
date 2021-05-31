package br.com.softbank.usuario.dto;

import java.io.Serializable;
import java.time.LocalDate;

import br.com.softbank.usuario.model.Usuario;

public class UsuarioResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String email;
	private boolean isAtivo;
	private String perfil;
	private LocalDate dataCadastro;
	
	public UsuarioResponseDTO() {
		
	}
	
	public UsuarioResponseDTO(Long id, String nome, String email,  boolean isAtivo, String perfil, LocalDate dataCadastro) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.isAtivo = isAtivo;
		this.perfil = perfil;
		this.dataCadastro = dataCadastro;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public boolean isAtivo() {
		return isAtivo;
	}

	public void setAtivo(boolean isAtivo) {
		this.isAtivo = isAtivo;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public UsuarioResponseDTO fromEntity(Usuario user) {
		return new UsuarioResponseDTO(user.getId(), user.getNome(), user.getEmail(), user.getIsAtivo(), user.getRoles().get(0).getNome(), user.getDataCadastro());
	}
}
