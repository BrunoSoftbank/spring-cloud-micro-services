package br.com.softbank.usuario.enuns;

public enum ErrosDefaultEnum {

	USUARIO_JA_CADASTRADO("Já existe um usuário cadastrado com o email %s"),
	USUARIO_NAO_ENCONTRADO("Usuário de id %d não encontrado"),
	TOKEN_INVALIDO("O token %s esta inválido");
	
	private String descricao;
	
	ErrosDefaultEnum(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
