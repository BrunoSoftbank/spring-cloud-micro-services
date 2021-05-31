package br.com.softbank.consultawebservice.enuns;

public enum ErrosDefaultEnum {

	CONSULTA_BY_ID_NAO_ENCONTRADA("Consulta de id %d não encontrada"),
	CONSULTA_BY_FILTERS_NAO_ENCONTRADA("Consulta de usuario_id %d e exame_id %d e laboratorio_id %d não encontrada");
	
	private String descricao;
	
	ErrosDefaultEnum(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
