package br.com.softbank.batch.enuns;

public enum ResourceEnum {

	LABORATORIOS("Laboratórios");
	
	ResourceEnum(String descricao) {
		this.descricao = descricao;
	}
	
	private String descricao;
	
	public String getDescricao() {
		return descricao;
	}
}
