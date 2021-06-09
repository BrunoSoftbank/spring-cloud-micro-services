package br.com.softbank.relatorio.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.softbank.relatorio.dto.RelatorioUsuarioDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaResponse {

	private Long id;
	@JsonProperty("usuario")
	private RelatorioUsuarioDTO usuarioDTO;
	@JsonProperty("exame")
	private ExameResponse exameDTO;
	@JsonProperty("laboratorio")
	private LaboratorioResponse laboratorioDTO;
}
