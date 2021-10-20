package br.com.softbank.relatorio.dto;

import java.io.Serializable;
import java.time.LocalDate;

import br.com.softbank.relatorio.annotations.RelatorioLabel;

@RelatorioLabel(name = "Usuários", order = 0)
public class RelatorioUsuarioDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String email;
	private Boolean ativo;
	private LocalDate dataCadastro;
	
	@RelatorioLabel(name = "Id", order = 1)
	public Long getId() {
		return id;
	}
	
	@RelatorioLabel(name = "Nome", order = 2)
	public String getNome() {
		return nome;
	}
	
	@RelatorioLabel(name = "Email", order = 3)
	public String getEmail() {
		return email;
	}
	
	@RelatorioLabel(name = "Status", order = 5)
	public Boolean isAtivo() {
		return ativo;
	}
	
	@RelatorioLabel(name = "Data do Cadastro", order = 4)
	public LocalDate getDataCadastro() {
		return dataCadastro;
	}
	
}
