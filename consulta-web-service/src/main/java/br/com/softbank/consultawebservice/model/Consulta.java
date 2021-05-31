package br.com.softbank.consultawebservice.model;

public class Consulta {

    private int id;
    private int usuarioId;
    private int exameId;
    private int laboratorioId;
    
    public Consulta() {
    	
    }

	public Consulta(int id, int usuarioId, int exameId, int laboratorioId) {
		this.id = id;
		this.usuarioId = usuarioId;
		this.exameId = exameId;
		this.laboratorioId = laboratorioId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}

	public int getExameId() {
		return exameId;
	}

	public void setExameId(int exameId) {
		this.exameId = exameId;
	}

	public int getLaboratorioId() {
		return laboratorioId;
	}

	public void setLaboratorioId(int laboratorioId) {
		this.laboratorioId = laboratorioId;
	}
   
 }
