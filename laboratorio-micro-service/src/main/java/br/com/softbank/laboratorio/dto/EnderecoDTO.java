package br.com.softbank.laboratorio.dto;

import javax.validation.constraints.NotBlank;

import br.com.softbank.laboratorio.model.Endereco;

public class EnderecoDTO {

	@NotBlank(message = "A cidade é obrigatório")
	private String cidade;
	@NotBlank(message = "O bairro é obrigatório")
	private String bairro;
	@NotBlank(message = "A rua é obrigatório")
	private String rua;
	@NotBlank(message = "O número é obrigatório")
	private String numero;
	
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
	
	public Endereco convertToEntity() {
		return new Endereco(null, cidade, bairro, rua, numero);
	}
	
}
