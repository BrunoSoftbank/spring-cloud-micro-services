package br.com.softbank.usuario.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 8125987210187005857L;
	
	private String message;

}