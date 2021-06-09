package br.com.softbank.exame.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusResponse implements Serializable {

	private static final long serialVersionUID = 8297614456959685721L;
	
	private Long id;
	private String descricao;
}
