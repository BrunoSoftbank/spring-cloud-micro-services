package br.com.softbank.exame.response;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDefault {

	private String description;
	private List<ErrorDefault> errors = new ArrayList<ErrorDefault>();
	

	public ResponseDefault(String description) {
		this.description = description;
	}
}
