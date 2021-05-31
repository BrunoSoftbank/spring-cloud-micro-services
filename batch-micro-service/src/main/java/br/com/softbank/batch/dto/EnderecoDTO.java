package br.com.softbank.batch.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EnderecoDTO {

	@JsonProperty("cidade")
	private String cidade;
	@JsonProperty("bairro")
	private String bairro;
	@JsonProperty("rua")
	private String rua;
	@JsonProperty("numero")
	private String numero;
	
	public EnderecoDTO() {
		
	}

	public EnderecoDTO(String cidade, String bairro, String rua, String numero) {
		this.cidade = cidade;
		this.bairro = bairro;
		this.rua = rua;
		this.numero = numero;
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
		return "EnderecoDTO [cidade=" + cidade + ", bairro=" + bairro + ", rua=" + rua + ", numero=" + numero + "]";
	}
}
