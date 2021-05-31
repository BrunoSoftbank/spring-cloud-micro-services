package br.com.softbank.relatorio.dto;

import java.io.Serializable;

import br.com.softbank.relatorio.annotations.RelatorioLabel;

@RelatorioLabel(name = "Laboratórios", order = 0)
public class RelatorioLaboratorioDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String cidade;
	private String bairro;
	private String rua;
	private String numero;
	private String status;
	
	public RelatorioLaboratorioDTO() {
		
	}

	public RelatorioLaboratorioDTO(Long id, String nome, String cidade, String bairro, String rua, String numero,
			String status) {
		this.id = id;
		this.nome = nome;
		this.cidade = cidade;
		this.bairro = bairro;
		this.rua = rua;
		this.numero = numero;
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
	@RelatorioLabel(name = "Cidade", order = 3)
	public String getCidade() {
		return cidade;
	}
	@RelatorioLabel(name = "Bairro", order = 4)
	public String getBairro() {
		return bairro;
	}
	@RelatorioLabel(name = "Rua", order = 5)
	public String getRua() {
		return rua;
	}
	@RelatorioLabel(name = "Numero", order = 6)
	public String getNumero() {
		return numero;
	}
	@RelatorioLabel(name = "Status", order = 7)
	public String getStatus() {
		return status;
	}
}
