package br.com.softbank.relatorio.dto;

import java.io.Serializable;

import br.com.softbank.relatorio.annotations.RelatorioLabel;

@RelatorioLabel(name = "Exames", order = 0)
public class RelatorioExameDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String tipo;
	private String status;
	
	public RelatorioExameDTO() {
		
	}	
	
	public RelatorioExameDTO(Long id, String nome, String tipo, String status) {
		this.id = id;
		this.nome = nome;
		this.tipo = tipo;
		this.status = status;
	}

	@RelatorioLabel(name = "Id", order = 1)
	public Long getId() {
		return id;
	}
	@RelatorioLabel(name = "Nome", order = 2)
	public String getNome() {
		return nome;
	}
	@RelatorioLabel(name = "Tipo", order = 3)
	public String getTipo() {
		return tipo;
	}
	@RelatorioLabel(name = "Status", order = 4)
	public String getStatus() {
		return status;
	}	
}
