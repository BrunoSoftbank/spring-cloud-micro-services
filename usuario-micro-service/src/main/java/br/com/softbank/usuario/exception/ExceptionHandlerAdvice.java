package br.com.softbank.usuario.exception;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.softbank.usuario.response.ErrorDefault;
import br.com.softbank.usuario.response.ResponseDefault;

@RestControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {
	
	private static final Logger LOG = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);
	
	@ExceptionHandler(TokenInvalidException.class)
	public ResponseEntity<ResponseDefault> tokenInvalidException(TokenInvalidException ex, HttpServletRequest request) {	
		LOG.error(this.getClass().getSimpleName() + ".tokenInvalidException(TokenInvalidException ex, HttpServletRequest request) " + ex.getMessage());		
		return new ResponseEntity<>(new ResponseDefault(ex.getMessage()), HttpStatus.NOT_FOUND);
	}	
	
	@ExceptionHandler(UsuarioNotFoundException.class)
	public ResponseEntity<ResponseDefault> tokenInvalidException(UsuarioNotFoundException ex, HttpServletRequest request) {	
		LOG.error(this.getClass().getSimpleName() + ".usuarioNotFoundException(UsuarioNotFoundException ex, HttpServletRequest request) " + ex.getMessage());
		return new ResponseEntity<>(new ResponseDefault(ex.getMessage()), HttpStatus.NOT_FOUND);
	}	
	
	@ExceptionHandler(UsuarioAlreadyExistsException.class)
	public ResponseEntity<ResponseDefault> usuarioAlreadyExistsException(UsuarioAlreadyExistsException ex, HttpServletRequest request) {	
		LOG.error(this.getClass().getSimpleName() + ".usuarioAlreadyExistsException(UsuarioAlreadyExistsException ex, HttpServletRequest request) " + ex.getMessage());		
		return new ResponseEntity<>(new ResponseDefault(ex.getMessage()), HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<ErrorDefault> errors = ex.getBindingResult().getFieldErrors()
        		.stream().map(error -> new ErrorDefault(error.getDefaultMessage()))
                .collect(Collectors.toList());
        
        return new ResponseEntity<>(new ResponseDefault("Invalid fields", errors), status);
	}
}
