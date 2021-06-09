package br.com.softbank.exame.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TipoExameNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 183680519599487871L;

	private String message;
}