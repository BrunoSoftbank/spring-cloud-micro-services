package br.com.softbank.laboratorio.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusResponse implements Serializable {

	private static final long serialVersionUID = 8311691076530509558L;
	
	private Long id;
	private String descricao;
}
