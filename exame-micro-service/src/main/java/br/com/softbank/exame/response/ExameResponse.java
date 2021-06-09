package br.com.softbank.exame.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExameResponse implements Serializable {

	private static final long serialVersionUID = 393773407512145912L;
	
	private Long id;
	private String nome;
	private StatusResponse status;
	private TipoResponse tipo;
	
}
