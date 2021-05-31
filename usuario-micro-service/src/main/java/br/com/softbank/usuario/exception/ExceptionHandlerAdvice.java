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

import br.com.softbank.usuario.dto.ErrorDTO;
import br.com.softbank.usuario.dto.ResponseDTO;

@RestControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {
	
	private static final Logger LOG = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);
	
	@ExceptionHandler(TokenInvalidException.class)
	public ResponseEntity<ResponseDTO> tokenInvalidException(TokenInvalidException ex, HttpServletRequest request) {	
		LOG.error(this.getClass().getSimpleName() + ".tokenInvalidException(TokenInvalidException ex, HttpServletRequest request) " + ex.getMessage());
		
		ResponseDTO response = new ResponseDTO(HttpStatus.NOT_FOUND, ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}	
	
	@ExceptionHandler(UsuarioNotFoundException.class)
	public ResponseEntity<ResponseDTO> tokenInvalidException(UsuarioNotFoundException ex, HttpServletRequest request) {	
		LOG.error(this.getClass().getSimpleName() + ".usuarioNotFoundException(UsuarioNotFoundException ex, HttpServletRequest request) " + ex.getMessage());
		
		ResponseDTO response = new ResponseDTO(HttpStatus.NOT_FOUND, ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}	
	
	@ExceptionHandler(UsuarioAlreadyExistsException.class)
	public ResponseEntity<ResponseDTO> usuarioAlreadyExistsException(UsuarioAlreadyExistsException ex, HttpServletRequest request) {	
		LOG.error(this.getClass().getSimpleName() + ".usuarioAlreadyExistsException(UsuarioAlreadyExistsException ex, HttpServletRequest request) " + ex.getMessage());
		
		ResponseDTO response = new ResponseDTO(HttpStatus.BAD_REQUEST, ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<ErrorDTO> errors = ex.getBindingResult().getFieldErrors()
        		.stream().map(error -> new ErrorDTO(error.getDefaultMessage()))
                .collect(Collectors.toList());
        
        ResponseDTO response = new ResponseDTO(HttpStatus.BAD_REQUEST, "Invalid fields", errors);
        return new ResponseEntity<>(response, status);
	}
}
