package br.com.softbank.exame.enuns;

public enum TipoExameEnum {

	ANALISE(1L, "Analise"),
	CLINICA(2L, "Clinica"),
	IMAGEM(3L, "Imagem");
	
	TipoExameEnum(Long id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	
	private Long id;
	private String descricao;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
