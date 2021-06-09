package br.com.softbank.consulta.exception;

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

import br.com.softbank.consulta.response.ErrorDefault;
import br.com.softbank.consulta.response.ResponseDefault;

@RestControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {
	
	private static final Logger LOG = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);
	
	@ExceptionHandler(ConsultaNotFoundException.class)
	public ResponseEntity<ResponseDefault> consultaNotFoundException(ConsultaNotFoundException ex, HttpServletRequest request) {	
		LOG.error(this.getClass().getSimpleName() + ".consultaNotFoundException(ConsultaNotFoundException ex, HttpServletRequest request) " + ex.getMessage());			
		return new ResponseEntity<>(new ResponseDefault(ex.getMessage()), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ConsultaAlreadyExistsException.class)
	public ResponseEntity<ResponseDefault> consultaAlreadyExistsException(ConsultaAlreadyExistsException ex, HttpServletRequest request) {	
		LOG.error(this.getClass().getSimpleName() + ".consultaAlreadyExistsException(ConsultaAlreadyExistsException ex, HttpServletRequest request) " + ex.getMessage());		
		return new ResponseEntity<>(new ResponseDefault(ex.getMessage()), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(GenericException.class)
	public ResponseEntity<ResponseDefault> genericException(GenericException ex, HttpServletRequest request) {	
		LOG.error(this.getClass().getSimpleName() + ".genericException(GenericException ex, HttpServletRequest request) " + ex.getMessage());		
		return new ResponseEntity<>(new ResponseDefault(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ResponseDefault> businessException(BusinessException ex, HttpServletRequest request) {	
		LOG.error(this.getClass().getSimpleName() + ".businessException(BusinessException ex, HttpServletRequest request) " + ex.getMessage());		
		return new ResponseEntity<>(new ResponseDefault(ex.getResponse().getDescription()), HttpStatus.PRECONDITION_FAILED);
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
