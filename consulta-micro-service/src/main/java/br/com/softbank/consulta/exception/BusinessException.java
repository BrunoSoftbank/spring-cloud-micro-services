package br.com.softbank.consulta.exception;

import br.com.softbank.consulta.response.ResponseDefault;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 919488034215728067L;

	private ResponseDefault response;
}
