package br.com.softbank.relatorio.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConsultaDTO {

	private Long id;
	@JsonProperty("usuario")
	private UsuarioDTO usuarioDTO;
	@JsonProperty("exame")
	private ExameDTO exameDTO;
	@JsonProperty("laboratorio")
	private LaboratorioDTO laboratorioDTO;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}
	public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
	}
	public ExameDTO getExameDTO() {
		return exameDTO;
	}
	public void setExameDTO(ExameDTO exameDTO) {
		this.exameDTO = exameDTO;
	}
	public LaboratorioDTO getLaboratorioDTO() {
		return laboratorioDTO;
	}
	public void setLaboratorioDTO(LaboratorioDTO laboratorioDTO) {
		this.laboratorioDTO = laboratorioDTO;
	}
}
