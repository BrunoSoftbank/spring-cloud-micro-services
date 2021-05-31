package br.com.softbank.relatorio.enuns;

public enum ErrosDefaultEnum {

	TIPO_RELATORIO_NAO_ENCONTRADO("Tipo de relatório %s não encontrado"),
	RECURSO_NAO_ENCONTRADO("Recurso %s não encontrado");
	
	private String descricao;
	
	ErrosDefaultEnum(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
