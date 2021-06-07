package br.com.softbank.usuario.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.google.gson.Gson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Usuario implements Serializable {

	private static final long serialVersionUID = 2966504704659103311L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String email;
	private String senha;
	private Boolean isAtivo;
	@ManyToMany(fetch= FetchType.EAGER)
	private List<Role> roles = new ArrayList<Role>();
	private LocalDate dataCadastro;
	

	public Usuario(String nome, String email, String senha, LocalDate dataCadastro) {
		super();
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.dataCadastro = dataCadastro;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}	
}
