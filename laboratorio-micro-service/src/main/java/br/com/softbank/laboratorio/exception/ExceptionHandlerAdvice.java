package br.com.softbank.laboratorio.exception;

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

import br.com.softbank.laboratorio.response.ErrorDefault;
import br.com.softbank.laboratorio.response.ResponseDefault;

@RestControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {
	
	private static final Logger LOG = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);
	
	@ExceptionHandler(LaboratorioNotFoundException.class)
	public ResponseEntity<ResponseDefault> laboratorioNotFound(LaboratorioNotFoundException ex, HttpServletRequest request) {	
		LOG.error(this.getClass().getSimpleName() + ".laboratorioNotFound(LaboratorioNotFoundException ex, HttpServletRequest request) " + ex.getMessage());
		return new ResponseEntity<>(new ResponseDefault(ex.getMessage()), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(LaboratorioAlreadyExistsException.class)
	public ResponseEntity<ResponseDefault> laboratorioAlreadyExistsException(LaboratorioAlreadyExistsException ex, HttpServletRequest request) {	
		LOG.error(this.getClass().getSimpleName() + ".laboratorioAlreadyExistsException(LaboratorioAlreadyExistsException ex, HttpServletRequest request) " + ex.getMessage());
		return new ResponseEntity<>(new ResponseDefault(ex.getMessage()), HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<ErrorDefault> errors = ex.getBindingResult().getFieldErrors()
        		.stream().map(error -> new ErrorDefault(error.getDefaultMessage()))
                .collect(Collectors.toList());

        return new ResponseEntity<>(new ResponseDefault("Campos inválidos", errors), status);
	}
}
