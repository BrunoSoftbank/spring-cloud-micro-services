package br.com.softbank.file.enuns;

public enum ResourceEnum {

	laboratorios(1L, "Laboratórios");
	
	ResourceEnum(Long id, String descricao) {
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
