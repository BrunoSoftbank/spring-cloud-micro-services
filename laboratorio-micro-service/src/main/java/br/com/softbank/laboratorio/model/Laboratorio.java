package br.com.softbank.laboratorio.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "softbank_laboratorio", name = "laboratorio")
@SequenceGenerator(name = "softbank_laboratorio.laboratorio_id_seq", sequenceName = "softbank_laboratorio.laboratorio_id_seq", allocationSize = 1)
public class Laboratorio implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "softbank_laboratorio.laboratorio_id_seq")
	private Long id;
	private String nome;
	@ManyToOne
	@JoinColumn(name = "endereco")
	private Endereco endereco;
	@ManyToOne
	@JoinColumn(name = "status")
	private Status status;
	
}
