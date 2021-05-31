package br.com.softbank.consulta.dto;

public class ExameDTO {

	private Long id;
	private String nome;
	private TipoDTO tipo;
	
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
	public TipoDTO getTipo() {
		return tipo;
	}
	public void setTipo(TipoDTO tipo) {
		this.tipo = tipo;
	}
}
