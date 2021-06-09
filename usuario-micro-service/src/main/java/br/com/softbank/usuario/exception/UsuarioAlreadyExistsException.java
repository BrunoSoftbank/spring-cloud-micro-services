package br.com.softbank.usuario.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 5119466394756323144L;

	private String message;

}
