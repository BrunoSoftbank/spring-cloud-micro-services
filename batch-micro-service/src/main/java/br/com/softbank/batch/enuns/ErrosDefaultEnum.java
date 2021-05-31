package br.com.softbank.batch.enuns;

public enum ErrosDefaultEnum {

	ARQUIVO_NAO_ENCONTRADO("Arquivo não disponível no servidor SFTP para Download");
	
	private String descricao;
	
	ErrosDefaultEnum(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
