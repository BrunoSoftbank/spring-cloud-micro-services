package br.com.softbank.file.response;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {

	private String description;
	private List<ErrorDefault> errors = new ArrayList<ErrorDefault>();
	
	public ResponseDTO(String description) {
		this.description = description;
	}
}
