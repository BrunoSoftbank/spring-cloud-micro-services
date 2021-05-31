package br.com.softbank.file.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

public class ResponseDTO {

	private HttpStatus httpStatus;
	private String description;
	private List<ErrorDTO> errors = new ArrayList<ErrorDTO>();
	
	public ResponseDTO() {
		
	}

	public ResponseDTO(HttpStatus httpStatus, String description, List<ErrorDTO> errors) {
		this.httpStatus = httpStatus;
		this.description = description;
		this.errors = errors;
	}

	public ResponseDTO(HttpStatus httpStatus, String description) {
		this.httpStatus = httpStatus;
		this.description = description;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<ErrorDTO> getErrors() {
		return errors;
	}
	public void setErrors(List<ErrorDTO> errors) {
		this.errors = errors;
	}

	@Override
	public String toString() {
		return "ResponseDTO [httpStatus=" + httpStatus + ", description=" + description + ", errors=" + errors + "]";
	}
}
