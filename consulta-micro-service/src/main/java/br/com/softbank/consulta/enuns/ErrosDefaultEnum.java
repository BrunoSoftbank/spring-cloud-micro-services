package br.com.softbank.consulta.enuns;

public enum ErrosDefaultEnum {

	GENERIC_ERROR("Ocorreu um erro interno no sistema, por favor tente mais tarde"),
	CONSULTA_NAO_ENCONTRADA("Consulta de id %d não encontrada"),
	CONSULTA_JA_EXISTENTE("O usuário %s já possui uma consulta de %s no(a) %s agendada");
	
	private String descricao;
	
	ErrosDefaultEnum(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
