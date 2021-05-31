package br.com.softbank.relatorio.enuns;

public enum ResourceEnum {

	exames(1L, "Exames"),
	laboratorios(2L, "Laboratórios"),
	usuarios(3L, "Usuários"),
	consultas(4L, "Consultas");
	
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
