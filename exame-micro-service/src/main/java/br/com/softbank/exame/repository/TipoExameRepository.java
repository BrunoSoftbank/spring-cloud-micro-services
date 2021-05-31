package br.com.softbank.exame.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.softbank.exame.model.Tipo;

@Repository
public interface TipoExameRepository extends JpaRepository<Tipo, Long> {

}
