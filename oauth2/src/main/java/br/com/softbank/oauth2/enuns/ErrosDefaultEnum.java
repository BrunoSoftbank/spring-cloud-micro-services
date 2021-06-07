package br.com.softbank.oauth2.enuns;

public enum ErrosDefaultEnum {

	USUARIO_NAO_AUTORIZADO("Usuario nao autorizado");
	
	private String descricao;
	
	ErrosDefaultEnum(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
