package br.com.softbank.exame.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Exame implements Serializable {


	private static final long serialVersionUID = -1987834032492120147L;
	
	@Id
	private String id;
	private String nome;
	private String descricao;


}
