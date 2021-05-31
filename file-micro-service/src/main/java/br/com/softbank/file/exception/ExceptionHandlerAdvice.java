package br.com.softbank.file.exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.softbank.file.dto.ResponseDTO;


@RestControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {
	
	private static final Logger LOG = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

	@ExceptionHandler(MediaTypeNotSupportedException.class)
	public ResponseEntity<ResponseDTO> mediaTypeNotSupportedException(MediaTypeNotSupportedException ex, HttpServletRequest request) {	
		LOG.error(this.getClass().getSimpleName() + ".mediaTypeNotSupportedException(MediaTypeNotSupportedException ex, HttpServletRequest request) " + ex.getMessage());
		ResponseDTO response = new ResponseDTO(HttpStatus.UNSUPPORTED_MEDIA_TYPE, ex.getMessage());
		LOG.error(ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}
	
	@ExceptionHandler(HeaderNotFoundException.class)
	public ResponseEntity<ResponseDTO> headerNotFoundException(HeaderNotFoundException ex, HttpServletRequest request) {	
		LOG.error(this.getClass().getSimpleName() + ".headerNotFoundException(HeaderNotFoundException ex, HttpServletRequest request) " + ex.getMessage());
		ResponseDTO response = new ResponseDTO(HttpStatus.UNSUPPORTED_MEDIA_TYPE, ex.getMessage());
		LOG.error(ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}
	
	@ExceptionHandler(InvalidFieldException.class)
	public ResponseEntity<ResponseDTO> invalidFieldException(InvalidFieldException ex, HttpServletRequest request) {	
		LOG.error(this.getClass().getSimpleName() + ".invalidFieldException(InvalidFieldException ex, HttpServletRequest request) " + ex.getMessage());
		ResponseDTO response = new ResponseDTO(HttpStatus.UNSUPPORTED_MEDIA_TYPE, ex.getMessage());
		LOG.error(ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}
}
