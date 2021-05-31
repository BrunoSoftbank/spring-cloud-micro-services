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

import br.com.softbank.consulta.dto.ErrorDTO;
import br.com.softbank.consulta.dto.ResponseDTO;

@RestControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {
	
	private static final Logger LOG = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);
	
	@ExceptionHandler(ConsultaNotFoundException.class)
	public ResponseEntity<ResponseDTO> consultaNotFoundException(ConsultaNotFoundException ex, HttpServletRequest request) {	
		LOG.error(this.getClass().getSimpleName() + ".consultaNotFoundException(ConsultaNotFoundException ex, HttpServletRequest request) " + ex.getMessage());		
		ResponseDTO response = new ResponseDTO(HttpStatus.NOT_FOUND, ex.getMessage());	
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ConsultaAlreadyExistsException.class)
	public ResponseEntity<ResponseDTO> consultaAlreadyExistsException(ConsultaAlreadyExistsException ex, HttpServletRequest request) {	
		LOG.error(this.getClass().getSimpleName() + ".consultaAlreadyExistsException(ConsultaAlreadyExistsException ex, HttpServletRequest request) " + ex.getMessage());		
		ResponseDTO response = new ResponseDTO(HttpStatus.BAD_REQUEST, ex.getMessage());	
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(GenericException.class)
	public ResponseEntity<ResponseDTO> genericException(GenericException ex, HttpServletRequest request) {	
		LOG.error(this.getClass().getSimpleName() + ".genericException(GenericException ex, HttpServletRequest request) " + ex.getMessage());		
		ResponseDTO response = new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());	
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ResponseDTO> businessException(BusinessException ex, HttpServletRequest request) {	
		LOG.error(this.getClass().getSimpleName() + ".businessException(BusinessException ex, HttpServletRequest request) " + ex.getMessage());		
		return new ResponseEntity<>(ex.getResponseDTO(), ex.getResponseDTO().getHttpStatus());
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<ErrorDTO> errors = ex.getBindingResult().getFieldErrors()
        		.stream().map(error -> new ErrorDTO(error.getDefaultMessage()))
                .collect(Collectors.toList());
        
        ResponseDTO response = new ResponseDTO(HttpStatus.BAD_REQUEST, "Campos inválidos", errors);
        return new ResponseEntity<>(response, status);
	}
}
