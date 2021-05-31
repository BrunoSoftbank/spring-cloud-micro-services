package br.com.softbank.batch.dto;

public class LaboratorioFromFileDTO {

	private String nome;
	private String cidade;
	private String bairro;
	private String rua;
	private String numero;
	
	public LaboratorioFromFileDTO() {
		
	}
	
	public LaboratorioFromFileDTO(String nome, String cidade, String bairro, String rua, String numero) {
		this.nome = nome;
		this.cidade = cidade;
		this.bairro = bairro;
		this.rua = rua;
		this.numero = numero;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Override
	public String toString() {
		return "LaboratorioFromFileDTO [nome=" + nome + ", cidade=" + cidade + ", bairro=" + bairro + ", rua=" + rua
				+ ", numero=" + numero + "]";
	}
}
