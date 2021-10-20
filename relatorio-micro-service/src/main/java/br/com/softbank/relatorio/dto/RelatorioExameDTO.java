package br.com.softbank.relatorio.dto;

import java.io.Serializable;

import br.com.softbank.relatorio.annotations.RelatorioLabel;

@RelatorioLabel(name = "Exames", order = 0)
public class RelatorioExameDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String nome;
	private String descricao;
	
	public RelatorioExameDTO() {
		
	}	
	
	public RelatorioExameDTO(String id, String nome, String descricao) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
	}

	@RelatorioLabel(name = "Id", order = 1)
	public String getId() {
		return id;
	}
	
	@RelatorioLabel(name = "Nome", order = 2)
	public String getNome() {
		return nome;
	}
	
	@RelatorioLabel(name = "Descricao", order = 3)
	public String getDescricao() {
		return descricao;
	}	
}
