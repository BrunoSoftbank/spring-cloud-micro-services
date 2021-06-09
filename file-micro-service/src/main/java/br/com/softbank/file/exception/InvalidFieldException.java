package br.com.softbank.file.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InvalidFieldException extends RuntimeException {

	private static final long serialVersionUID = 4055248107640845937L;
	
	private String message;
}

