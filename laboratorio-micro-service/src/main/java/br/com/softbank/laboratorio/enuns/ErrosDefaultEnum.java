package br.com.softbank.laboratorio.enuns;

public enum ErrosDefaultEnum {

	LABORATORIO_NAO_ENCONTRADO("Laboratório de id %d não encontrado"),
	LABORATORIO_JA_CADASTRADO("Já existe um laboratório com o nome %s cadastrado");
	
	private String descricao;
	
	ErrosDefaultEnum(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
