package br.com.softbank.exame.enuns;

public enum ErrosDefaultEnum {

	EXAME_NAO_ENCONTRADO("Exame de id %s não encontrado");
	
	private String descricao;
	
	ErrosDefaultEnum(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
