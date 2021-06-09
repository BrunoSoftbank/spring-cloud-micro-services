package br.com.softbank.laboratorio.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LaboratorioAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 183680519599487871L;

	private String message;
}
