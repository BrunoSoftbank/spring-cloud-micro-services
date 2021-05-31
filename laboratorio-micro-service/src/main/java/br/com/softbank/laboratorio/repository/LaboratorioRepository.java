package br.com.softbank.laboratorio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.softbank.laboratorio.model.Laboratorio;

@Repository
public interface LaboratorioRepository extends JpaRepository<Laboratorio, Long> {

	Optional<Laboratorio> findByNome(String nome);
}
