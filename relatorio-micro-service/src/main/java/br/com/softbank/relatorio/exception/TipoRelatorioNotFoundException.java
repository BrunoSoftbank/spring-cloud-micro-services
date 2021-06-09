package br.com.softbank.relatorio.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TipoRelatorioNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 2954889151837788109L;
	
	private String message;
}
