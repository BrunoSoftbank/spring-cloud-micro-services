package br.com.softbank.relatorio.enuns;

public enum TipoRelatorioEnum {

	pdf(1L, "Pdf"),
	xlsx(2L, "Xlsx"),
	txt(3L, "Txt"),
	word(4L, "Word");
	
	TipoRelatorioEnum(Long id, String descricao) {
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
