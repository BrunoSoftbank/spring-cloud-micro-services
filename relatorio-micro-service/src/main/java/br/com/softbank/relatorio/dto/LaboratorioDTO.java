package br.com.softbank.relatorio.dto;

public class LaboratorioDTO {

	private Long id;
	private String nome;
	private EnderecoDTO endereco;
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
	public EnderecoDTO getEndereco() {
		return endereco;
	}
	public void setEndereco(EnderecoDTO endereco) {
		this.endereco = endereco;
	}
	public StatusDTO getStatus() {
		return status;
	}
	public void setStatus(StatusDTO status) {
		this.status = status;
	}
}
