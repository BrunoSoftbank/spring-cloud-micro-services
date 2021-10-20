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
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "softbank_laboratorio", name = "status")
@SequenceGenerator(name = "softbank_laboratorio.status_id_seq", sequenceName = "softbank_laboratorio.status_id_seq", allocationSize = 1)
public class Status implements Serializable {

	private static final long serialVersionUID = 7820308238740378631L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "softbank_laboratorio.status_id_seq")
    private Long id;
	private String descricao;
}
