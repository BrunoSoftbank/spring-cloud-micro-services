package br.com.softbank.relatorio.dto;

import br.com.softbank.relatorio.annotations.RelatorioLabel;

@RelatorioLabel(name = "Consultas", order = 0)
public class RelatorioConsultaDTO {

	private Long id;
	private String nomeUsuario;
	private String nomeExame;
	private String nomeLaboratorio;
	
	public RelatorioConsultaDTO() {
		
	}
	
	public RelatorioConsultaDTO(Long id, String nomeUsuario, String nomeExame, String nomeLaboratorio) {
		this.id = id;
		this.nomeUsuario = nomeUsuario;
		this.nomeExame = nomeExame;
		this.nomeLaboratorio = nomeLaboratorio;
	}

	@RelatorioLabel(name = "Id", order = 1)
	public Long getId() {
		return id;
	}
	
	@RelatorioLabel(name = "Usuario", order = 2)
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	
	@RelatorioLabel(name = "Exame", order = 3)
	public String getNomeExame() {
		return nomeExame;
	}
	
	@RelatorioLabel(name = "Laboratório", order = 4)
	public String getNomeLaboratorio() {
		return nomeLaboratorio;
	}
}
