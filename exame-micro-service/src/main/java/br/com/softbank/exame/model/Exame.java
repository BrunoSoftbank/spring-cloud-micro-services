package br.com.softbank.exame.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.google.gson.Gson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Exame implements Serializable {


	private static final long serialVersionUID = -1987834032492120147L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	@ManyToOne
	@JoinColumn(name = "status")
	private Status status;
	@ManyToOne
	@JoinColumn(name = "tipo")
	private Tipo tipo;
	
	public Exame(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
}
