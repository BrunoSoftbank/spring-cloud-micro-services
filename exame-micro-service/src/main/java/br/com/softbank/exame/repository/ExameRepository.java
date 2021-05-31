package br.com.softbank.exame.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.softbank.exame.model.Exame;

@Repository
public interface ExameRepository extends JpaRepository<Exame, Long> {

	List<Exame> findByNomeContainingIgnoringCase(String nome);
}
