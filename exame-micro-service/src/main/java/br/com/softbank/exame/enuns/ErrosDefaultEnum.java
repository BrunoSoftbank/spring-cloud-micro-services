package br.com.softbank.exame.enuns;

public enum ErrosDefaultEnum {

	EXAME_NAO_ENCONTRADO("Exame de id %d não encontrado"),
	TIPO_EXAME_NAO_ENCONTRADO("Tipo de exame de id %d não encontrado");
	
	private String descricao;
	
	ErrosDefaultEnum(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
