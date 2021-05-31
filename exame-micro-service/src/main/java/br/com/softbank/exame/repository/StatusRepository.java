package br.com.softbank.exame.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.softbank.exame.model.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {

}
