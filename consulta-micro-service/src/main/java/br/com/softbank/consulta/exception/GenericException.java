package br.com.softbank.consulta.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GenericException extends RuntimeException {

	private static final long serialVersionUID = 919488034215728067L;

	private String message;
}
