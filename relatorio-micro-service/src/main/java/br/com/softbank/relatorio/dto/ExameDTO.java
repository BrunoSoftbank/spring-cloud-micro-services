package br.com.softbank.relatorio.dto;

public class ExameDTO {

	private Long id;
	private String nome;
	private TipoDTO tipo;
	private StatusDTO status;
	
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
	public StatusDTO getStatus() {
		return status;
	}
	public void setStatus(StatusDTO status) {
		this.status = status;
	}
}
