package br.com.softbank.laboratorio.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "id")
@Entity
@Table(schema = "softbank_laboratorio", name = "endereco")
@SequenceGenerator(name = "softbank_laboratorio.endereco_id_seq", sequenceName = "softbank_laboratorio.endereco_id_seq", allocationSize = 1)
public class Endereco implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "softbank_laboratorio.endereco_id_seq")
	private Long id;
	private String cidade;
	private String bairro;
	private String rua;
	private String numero;
}
