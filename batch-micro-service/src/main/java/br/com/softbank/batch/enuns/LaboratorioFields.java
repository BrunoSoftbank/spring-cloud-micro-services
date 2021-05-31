package br.com.softbank.batch.enuns;

public enum LaboratorioFields {

	NOME(0),
	CIDADE(1),
	BAIRRO(2),
	RUA(3),
	NUMERO(4);
	
	LaboratorioFields(int position) {
		this.position = position;
	}
	
	private int position;
	
	public int getPosition() {
		return position;
	}
}
