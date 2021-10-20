package br.com.softbank.exame.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.softbank.exame.model.Exame;

@Repository
public interface ExameRepository extends MongoRepository<Exame, String> {

}
