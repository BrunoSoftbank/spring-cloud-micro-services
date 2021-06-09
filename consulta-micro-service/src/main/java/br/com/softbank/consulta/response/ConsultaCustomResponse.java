package br.com.softbank.consulta.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaCustomResponse {

	private Long id;
	@JsonProperty("usuario")
	private UsuarioResponse usuarioDTO;
	@JsonProperty("exame")
	private ExameResponse exameDTO;
	@JsonProperty("laboratorio")
	private LaboratorioResponse laboratorioDTO;
	
}
