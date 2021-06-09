package br.com.softbank.exame.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoResponse implements Serializable {

	private static final long serialVersionUID = -313540096303574372L;
	
	private Long id;
	private String descricao;
}
