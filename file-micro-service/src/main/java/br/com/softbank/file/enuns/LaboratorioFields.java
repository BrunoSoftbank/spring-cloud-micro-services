package br.com.softbank.file.enuns;

public enum LaboratorioFields {

	NOME(0, "Nome"),
	CIDADE(1, "Cidade"),
	BAIRRO(2, "Bairro"),
	RUA(3, "Rua"),
	NUMERO(4, "Número");
	
	LaboratorioFields(int position, String description) {
		this.position = position;
		this.description = description;
	}
	
	private int position;
	String description;
	
	public int getPosition() {
		return position;
	}
	
	public String getDescription() {
		return description;
	}
}
