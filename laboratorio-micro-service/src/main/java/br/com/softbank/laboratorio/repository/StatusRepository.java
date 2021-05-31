package br.com.softbank.laboratorio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.softbank.laboratorio.model.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {

}
