package br.com.softbank.usuario.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {

	private HttpStatus httpStatus;
	private String description;
	private List<ErrorDTO> errors = new ArrayList<ErrorDTO>();
	
	public ResponseDTO(HttpStatus httpStatus, String description) {
		this.httpStatus = httpStatus;
		this.description = description;
	}
}
