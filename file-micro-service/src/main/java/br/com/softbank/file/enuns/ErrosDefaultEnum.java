package br.com.softbank.file.enuns;

public enum ErrosDefaultEnum {

	TIPO_MIDIA_NAO_SUPORTADO("Apenas arquivos com extensão xlsx são suportados para este recurso"),
	HEADER_NOT_FOUND("Deve existir uma coluna na posição %d chamada %s no arquivo de Laboratórios"),
	INVALID_FIELD("O campo %s na linha %d do arquivo de Laboratórios é obrigatório");
	
	private String descricao;
	
	ErrosDefaultEnum(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
